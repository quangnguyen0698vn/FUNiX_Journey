--Chapter 13 indexes and constraints
SELECT first_name, last_name
FROM customer
WHERE last_name like 'Y%'

-- index creation

create index idx_email on [dbo].[customer](email) 

drop index if exists idx_email on [dbo].[customer]

create unique index idx_email on customer(email)

insert into customer  (store_id, first_name, last_name, email, address_id, active)
values (1,'ALAN','KAHN', 'ALAN.KAHN@sakilacustomer.org', 394, 1);

create index idx_full_name on customer (last_name, first_name);

select customer_id, first_name, last_name
from customer
where first_name like 'S%' and last_name like 'P%'

-- data windows

select datepart(quarter, payment_date) quarter, 
    DATENAME(month, payment_date) month_nm,
    sum(amount) monthly_sales,
    max(sum(amount))
        over () max_overall_sales,
    max(sum(amount))
        over (partition by datepart(quarter, payment_date)) max_qrtr_sales
from payment
where year(payment_date) = 2005
group by datepart(quarter, payment_date), DATENAME(month, payment_date)
order by 3

-- localized sorting
SELECT datepart(quarter, payment_date) quarter,
    datename(month, payment_date) month_nm,
    sum(amount) monthly_sales,
    rank() over (order by sum(amount) desc) sales_rank
FROM payment
WHERE year(payment_date) = 2005
GROUP BY datepart(quarter, payment_date), datename(month, payment_date), datepart(month, payment_date)
ORDER BY 1, datepart(month, payment_date)

select datepart(quarter, payment_date) quarter,
    datename(month, payment_date) month_nm,
    sum(amount) monthly_sales,
    rank() over (partition by datepart(quarter, payment_date) order by sum(amount) desc) qtr_sales_rank
from payment
where year(payment_date) = 2005
group by datepart(quarter, payment_date),
    datename(month, payment_date), datepart(month, payment_date)
order by 1, datepart(month, payment_date)

--ranking function
/*

row_number
Returns a unique number for each row, with rankings arbitrarily assigned in case
of a tie

rank
Returns the same ranking in case of a tie, with gaps in the rankings

dense_rank
Returns the same ranking in case of a tie, with no gaps in the rankings

*/

select customer_id, count(*) num_rentals,
    row_number() over (order by count(*) desc) row_number_rnk,
    rank() over (order by count(*) desc) rank_rnk,
    dense_rank() over (order by count(*) desc) dense_rank_rnk
from rental
group by customer_id
order by 2 desc;


-- generating multiple ranking
select customer_id,
    datename(month, rental_date) rental_month,
    count(*) num_rentals
from rental
group by customer_id, datename(month, rental_date)
order by 2, 3 desc

select customer_id,
    datename(month, rental_date) rental_month,
    count(*) num_rentals,
    rank() over (partition by datename(month, rental_date) order by count(*) desc) rank_rnk
from rental
group by customer_id, datename(month, rental_date)
order by 2, 3 desc


select customer_id, rental_month, num_rentals, rank_rnk ranking
from
(
    select customer_id,
        datename(month, rental_date) rental_month,
        count(*) num_rentals,
        rank() over (partition by datename(month, rental_date) order by count(*) desc) rank_rnk
    from rental
    group by customer_id, datename(month, rental_date)    
) cust_rankings
where rank_rnk <= 5
order by rental_month, num_rentals desc, rank_rnk

--Reporting functions

select datename(month, payment_date) payment_month,
    amount,
    sum(amount)
        over (partition by datename(month, payment_date)) monthly_total,
    sum(amount) over () grand_total
from payment
where amount >= 10
order by 1

--add percentage

select datename(month, payment_date) payment_month,
    sum(amount) month_total,
    round( 100 * sum(amount) / sum(sum(amount)) over (), 2) ptc_of_total
from payment
group by datename(month, payment_date)


select datename(month, payment_date) payment_month,
    sum(amount) month_total,
    case sum(amount)
        when max(sum(amount)) over () then 'Highest'
        when min(sum(amount)) over () then 'Lowest'
        else 'Middle'
    end descriptor
from payment
group by datename(month, payment_date)

-- window frames

select year(payment_date) year, datepart(wk, payment_date) payment_week,
    sum(amount) week_total,
    sum(sum(amount))
        over (order by year(payment_date), datepart(wk, payment_date)
            rows unbounded preceding) rolling_sum
from payment
group by year(payment_date), datepart(wk, payment_date)
order by year(payment_date), datepart(wk, payment_date)

--

select year(payment_date) year, datepart(wk, payment_date) payment_week,
    sum(amount) week_total,
    avg(sum(amount))
        over (order by year(payment_date), datepart(wk, payment_date)
            rows between 1 preceding and 1 following) rolling_3wk_avg
from payment
group by year(payment_date), datepart(wk, payment_date)
order by year(payment_date), datepart(wk, payment_date)

--range => need more information

--lag and lead
select year(payment_date) year, datepart(wk, payment_date) payment_week,
    sum(amount) week_total,
    lag(sum(amount), 1)
        over (order by year(payment_date), datepart(wk, payment_date)) prev_wk_tot,
    lead(sum(amount), 1)
        over (order by year(payment_date), datepart(wk, payment_date)) next_wk_tot
from
    payment
group by 
    year(payment_date), datepart(wk, payment_date)
order by 1

--
select year(payment_date) year, datepart(wk, payment_date) payment_week,
    sum(amount) week_total,
    round( 
        (sum(amount) - lag(sum(amount),1) over (order by year(payment_date), datepart(wk, payment_date)))
        /
        lag(sum(amount),1) over (order by year(payment_date), datepart(wk, payment_date)) * 100
        ,1
    ) pct_dif,
    lag(sum(amount), 1)
        over (order by year(payment_date), datepart(wk, payment_date)) prev_wk_tot,
    lead(sum(amount), 1)
        over (order by year(payment_date), datepart(wk, payment_date)) next_wk_tot
from
    payment
group by 
    year(payment_date), datepart(wk, payment_date)
order by 1

--Column Value Concatenation

select f.title, string_agg(a.last_name,', ') within group (order by a.last_name)
from actor a
    inner join film_actor fa
    on a.actor_id = fa.actor_id
    inner join film f
    on fa.film_id = f.film_id
group by f.title
having count(*) = 3