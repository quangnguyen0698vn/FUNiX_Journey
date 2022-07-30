--8.1 Adding and Subtracting Days, Months, and Years
/*
You need to add or subtract some number of days, months, or years from a date. For
example, using the HIREDATE for employee CLARK, you want to return six differ‐
ent dates: five days before and after CLARK was hired, five months before and after CLARK was hired, and, finally, five years before and after CLARK was hired. CLARK
was hired on 09-JUN-2006, so you want to return the following result set:
*/

SELECT 
    EMP.HIREDATE as original_date,
    DATEADD(day, -5, EMP.HIREDATE) as hd_minus_5D,
    DATEADD(DAY, 5, EMP.HIREDATE) as hd_plus_5D,
    DATEADD(month, -5, EMP.HIREDATE) as hd_minus_5M,
    DATEADD(month, 5, EMP.HIREDATE) as hd_plus_5M,
    DATEADD(year, -5, EMP.HIREDATE) as hd_minus_5Y,
    DATEADD(year, 5, EMP.HIREDATE) as hd_plus_5Y
FROM EMP
WHERE EMP.DEPTNO = 10;

--8.2 Determining the Number of Days Between Two Dates
/*
You want to find the difference between two dates and represent the result in days.
For example, you want to find the difference in days between the HIREDATEs of
employee ALLEN and employee WARD.
*/

SELECT DATEDIFF(DAY, allen_hd, ward_hd)
FROM    
    (
        SELECT EMP.HIREDATE as ward_hd
        FROM EMP
        WHERE EMP.ENAME = 'WARD'
    ) x,
    (
        SELECT EMP.HIREDATE as allen_hd
        FROM EMP
        WHERE EMP.ENAME = 'ALLEN'
    ) y

--8.3 Determining the Number of Business Days Between Two Dates
/*
Given two dates, you want to find how many “working” days are between them,
including the two dates themselves. For example, if January 10th is a Tuesday and
January 11th is a Monday, then the number of working days between these two dates
is two, as both days are typical workdays. For this recipe, a “business day” is defined as any day that is not Saturday or Sunday.
*/

select * from t1;
select * from t10;


-- CREATE FUNCTION [dbo].[ufn_GenerateIntegers] ( @MinValue INT, @MaxValue INT )
-- RETURNS @Integers TABLE ( [IntValue] INT )
-- AS
-- BEGIN
--     WHILE @MinValue <= @MaxValue
--     BEGIN
--         INSERT INTO @Integers ( [IntValue] ) VALUES ( @MinValue )
--         SET @MinValue = @MinValue + 1
--     END

--     RETURN
-- END
-- GO

drop if exists T500;
create table T500(id integer)

insert into T500
select *
from [dbo].[ufn_GenerateIntegers] ( 1, 500 )

select * from T500;

select 
    sum(
        case when datename(dw,jones_hd+t500.id-1) in ('SATURDAY', 'SUNDAY') 
        then 0 else 1 end
    ) as days
-- select * 
from 
(
    select max(case when ename = 'BLAKE' then hiredate end) as blake_hd,
            max(case when ename = 'JONES' then hiredate end) as jones_hd
    from emp
    where ename in ('BLAKE', 'JONES')
) x, t500
where t500.id <= datediff(day,jones_hd, blake_hd) + 1

--8.4 Determining the Number of Months or Years Between Two Dates
/*
You want to find the difference between two dates in terms of either months or years.
For example, you want to find the number of months between the first and last
employees hired, and you also want to express that value as some number of years.
*/

select
    datediff(month, min_hd, max_hd) month_diff,
    datediff(year, min_hd, max_hd) year_diff
from
    (
        select min(hiredate) min_hd, max(hiredate) max_hd
        from emp
    ) x

--8.5 Determining the Number of Seconds, Minutes, or Hours Between Two Dates
/*
You want to return the difference in seconds between two dates. For example, you
want to return the difference between the HIREDATEs of ALLEN and WARD in seconds, minutes, and hours.
*/

select hiredate from emp;

select 
    datediff(hour, allen_hd, ward_hd) hour_diff,
    datediff(minute, allen_hd, ward_hd) min_diff,
    datediff(second, allen_hd, ward_hd) sec_diff
from
(
    select 
        max(case when ename = 'WARD' then hiredate end) as ward_hd,
        max(case when ename = 'ALLEN' then hiredate end) as allen_hd
    from emp
) x

--8.6 Counting the Occurrences of Weekdays in a Year 
/*
You want to count the number of times each weekday occurs in one year.
*/

with x (start_date, end_date) as (
    select 
        start_date,
        dateadd(year, 1, start_date) end_date
    from
    (
        select cast(
            cast(year(getdate()) as varchar) + '-01-01'
            as datetime
        ) start_date
        --from t1
    ) tmp
    union all
    select dateadd(day,1,start_date), end_date
    from x
    where dateadd(day,1,start_date) < end_date
)
-- select * from x option (maxrecursion 366)
select datename(dw, start_date), count(*)
from x
group by datename(dw, start_date)
order by 1
option (maxrecursion 366)

-- 8.7 Determining the Date Difference Between the Current Record and the Next Record
/*
You want to determine the difference in days between two dates (specifically dates
stored in two different rows). For example, for every employee in DEPTNO 10, you
want to determine the number of days between the day they were hired and the day
the next employee (can be in another department) was hired.
*/

select x.ename, x.hiredate, x.next_hd,
    datediff(day, x.hiredate, x.next_hd) as diff
from
(
    select deptno, ename, hiredate, 
    lead(hiredate) over (order by hiredate) as next_hd
    from emp e
    -- where e.DEPTNO = 10
) x
where x.DEPTNO = 10

--9.1 Determining Whether a Year Is a Leap Year
/*
You want to determine whether the current year is a leap year.
*/

select 
    case when isdate(concat(datepart(year, getdate()),'0229')) = 0 then 'Not a leap year'
    else 'leap year' end;
-- select coalesce(DATEPART(day, cast(concat(datepart(year, getdate()),'0229') as date)), 28);

--9.2 Determining the Number of Days in a Year
/*
You want to count the number of days in the current year.
*/
select DATEDIFF(
    day,
    convert(date, concat(DATEPART(year, getdate()),'0101')),
    DATEADD(year,1,convert(date, concat(DATEPART(year, getdate()),'0101')))
)

--9.3 Extracting Units of Time from a Date
/*
You want to break the current date down into six parts: day, month, year, second,
minute, and hour. You want the results to be returned as numbers.
*/

select
    datepart(hour, getdate()) hr,
    datepart(minute, getdate()) min,
    datepart(second, getdate()) sec,
    datepart(day, getdate()) dy,
    datepart(month, getdate()) mon,
    datepart(year, getdate()) yr
from t1

-- 9.4 Determining the First and Last Days of a Month
SELECT
    DATEADD(day, -DATEPART(day, getdate()) + 1, getdate()) start_date,
    DATEADD(day,-1,DATEADD(month,1,DATEADD(day, -DATEPART(day, getdate()) + 1, getdate()) )) end_date
from t1

--9.5 Determining All Dates for a Particular Weekday Throughout a Year
/*
You want to find all the dates in a year that correspond to a given day of the week. For example, you may want to generate a list of Fridays for the current year.
*/

with x (start_date, end_date) as (
    select 
        start_date,
        dateadd(year, 1, start_date) end_date
    from
    (
        select cast(
            cast(year(getdate()) as varchar) + '-01-01'
            as datetime
        ) start_date
        --from t1
    ) tmp
    union all
    select dateadd(day,1,start_date), end_date
    from x
    where dateadd(day,1,start_date) < end_date
)
select start_date
from x  
where DATENAME(dw,start_date) = 'Friday'
option (maxrecursion 366)

-- with x(start_date, end_date) as (
-- select
--     start_date,
--     dateadd(year, 1, start_date) end_date
-- from
--     (
--         select (
--             cast(concat(convert(varchar,datepart(year,getdate())), '0101') as date)
--         ) start_date
--     ) tmp
--     union all
--     select dateadd(day, 1, start_date), end_date
--     from x
--     where dateadd(day, 1, start_date) < end_date
-- )
-- select * from x
-- option (maxrecursion 500);

-- 9.6 Determining the Date of the First and Last Occurrences of a Specific Weekday in a Month

with x (dy, mth, is_monday) as 
(
    select dy, month(dy) mth,
        case when DATENAME(dw,dy) = 'Monday' then 1 else 0 end is_monday
    from (
        select dateadd(day,-day(getdate()) + 1,getdate()) dy
    ) tmp1
    union all
    select dateadd(day, 1, dy), mth,
        case when datename(dw,dateadd(day, 1, dy)) = 'Monday' then 1 else 0 end is_monday
    from x
    where month(dateadd(day, 1, dy)) = mth
)
select min(dy) first_monday,
    max(dy) last_monday
from x
where is_monday = 1

--9.7 Creating a Calendar
/*
You want to create a calendar for the current month. The calendar should be format‐
ted like a calendar you might have on your desk: seven columns across and (usually)
five rows down.
*/

-- select datepart(dw, dateadd(day,-day(getdate())+1, getdate()))

with x(dy, dm, mth, dw, wk) as 
(
    select
        dy,
        day(dy) dm,
        datepart(month, dy) mth,
        datepart(dw, dy) dw,
        case when datepart(dw, dy) = 1 then datepart(ww, dy) - 1 else datepart(ww,dy) end wk
    from (
        select dateadd(day,-day(getdate())+1, getdate()) dy
    ) x
    union all
    select 
        dateadd(day, 1, dy),
        day(dateadd(day, 1, dy)) dm,
        datepart(month, dateadd(day, 1, dy)) mth,
        datepart(dw, dateadd(day, 1, dy)) dw,
        case when datepart(dw, dateadd(day, 1, dy)) = 1 then datepart(ww, dateadd(day, 1, dy)) - 1
        else datepart(ww, dateadd(day, 1, dy)) end wk
    from x
    where datepart(month, dateadd(day, 1, dy)) = mth
)
select
    case dw when 2 then dm end as Mo,
    case dw when 3 then dm end as Tu,
    case dw when 4 then dm end as We,
    case dw when 5 then dm end as Th,
    case dw when 6 then dm end as Fr,
    case dw when 7 then dm end as Sa,
    case dw when 1 then dm end as Su, wk
from x
go

with x(dy, dm, mth, dw, wk) as 
(
    select
        dy,
        day(dy) dm,
        datepart(month, dy) mth,
        datepart(dw, dy) dw,
        case when datepart(dw, dy) = 1 then datepart(ww, dy) - 1 else datepart(ww,dy) end wk
    from (
        select dateadd(day,-day(getdate())+1, getdate()) dy
    ) x
    union all
    select 
        dateadd(day, 1, dy),
        day(dateadd(day, 1, dy)) dm,
        datepart(month, dateadd(day, 1, dy)) mth,
        datepart(dw, dateadd(day, 1, dy)) dw,
        case when datepart(dw, dateadd(day, 1, dy)) = 1 then datepart(ww, dateadd(day, 1, dy)) - 1
        else datepart(ww, dateadd(day, 1, dy)) end wk
    from x
    where datepart(month, dateadd(day, 1, dy)) = mth
)
select
    min(case dw when 2 then dm end) as Mo,
    min(case dw when 3 then dm end) as Tu,
    min(case dw when 4 then dm end) as We,
    min(case dw when 5 then dm end) as Th,
    min(case dw when 6 then dm end) as Fr,
    min(case dw when 7 then dm end) as Sa,
    min(case dw when 1 then dm end) as Su
from x
group by wk
order by wk

-- select * from x
-- order by 1

--9.8 Listing Quarter Start and End Dates for the Year
/*
You want to return the start and end dates for each of the four quarters of a given year.
*/

with x(dy, cnt) as (
    select dateadd(d, -datepart(dy,getdate())+1, getdate()) dy, 1 cnt
    union all
    select dateadd(m,3,dy), cnt+1
    from x
    where cnt+1 <= 4
)
select dy, datepart(q,dy) QTR from x;

with x(dy, cnt) as (
    select dateadd(d, -datepart(dy,getdate())+1, getdate()) dy, 1 cnt
    union all
    select dateadd(m,3,dy), cnt+1
    from x
    where cnt+1 <= 4
)
select
    datepart(q,dy) QTR,
    dy Q_start,
    dateadd(d,-1,dateadd(m,3,dy)) Q_end
from x
order by 1

-- 9.10 Filling in Missing Dates
/*
You need to generate a row for every date (or every month, week, or year) within a
given range. Such rowsets are often used to generate summary reports. For example,
you want to count the number of employees hired every month of every year in
which any employee has been hired.
*/

select distinct datepart(year, hiredate) as year
from emp

/*
generate the first date of each month of each year in the range of 1980 - 1983
*/

select
    dateadd(day, -datepart(dy,min(hiredate)) + 1, min(hiredate)) start_date, 
    dateadd(year, 1, 
        dateadd(day, -datepart(dy,max(hiredate)) + 1, max(hiredate))
    ) end_date
from emp

with x(start_date, end_date) as 
(

    select
        dateadd(day, -datepart(dy,min(hiredate)) + 1, min(hiredate)) start_date, 
        dateadd(year, 1, 
            dateadd(day, -datepart(dy,max(hiredate)) + 1, max(hiredate))
        ) end_date
    from emp
    
    union all

    select 
        dateadd(month, 1, start_date), end_date
    from x
    where dateadd(month, 1, start_date) < end_date
)
select 
    -- *
    datepart(year,start_date) year, datepart(month, start_date) month, count(emp.hiredate) as numberOfEmployeeHired
from 
    x left outer join emp
    on emp.hiredate between start_date and dateadd(day, -1, dateadd(month, 1, start_date))
group by start_date
order by 1

go 

select count(*) as total from emp 

-- 9.11 Searching on Specific Units of Time
/*
You want to search for dates that match a given month, day of the week, or some
other unit of time. For example, you want to find all employees hired in February or
December, as well as employees hired on a Tuesday.
*/

select 
    ename, hiredate, 
    case when datename(dw, hiredate) = 'Tuesday' then 'Tuesday' else '' end Tuesday
from
    emp
where DATENAME(month, hiredate) in ('February', 'December')
    or DATENAME(dw, hiredate) = 'Tuesday'

--9.12 Comparing Records Using Specific Parts of a Date
/*
You want to find which employees have been hired on the same month and weekday.
For example, if an employee was hired on Monday, March 10, 2008, and another
employee was hired on Monday, March 2, 2001, you want those two to come up as a
match since the day of week and month match. In table EMP, only three employees
meet this requirement. You want to return the following result set:
MSG
------------------------------------------------------
JAMES was hired on the same month and weekday as FORD
SCOTT was hired on the same month and weekday as JAMES
SCOTT was hired on the same month and weekday as FORD
*/

select 
    a.ename +
    ' was hired on the same month and weekday as ' +
    b.ename msg
from
    emp a, emp b
where
    datename(dw,a.hiredate) = datename(dw,b.hiredate) and
    datename(m, a.hiredate) = datename(m, a.hiredate) and
    a.empno < b.empno
order by a.ename    


--9.13 Identifying Overlapping Date Ranges
drop table if exists emp_project;

create table emp_project(
    empno int,
    ename varchar(50),
    proj_id int,
    proj_start date,
    proj_end date
);

insert into emp_project values
(7782,'CLARK',1,'20050616','20050618'),
(7782,'CLARK',4,'20050619','20050624'),
(7782,'CLARK',7,'20050622','20050625'),
(7782,'CLARK',10,'20050625','20050628'),
(7782,'CLARK',13,'20050628','20050702'),
(7839,'KING',2,'20050617','20050621'),
(7839,'KING',8,'20050623','20050625'),
(7839,'KING',14,'20050629','20050630'),
(7839,'KING',11,'20050626','20050627'),
(7839,'KING',5,'20050620','20050624'),
(7934,'MILLER',3,'20050618','20050622'),
(7934,'MILLER',12,'20050627','20050628'),
(7934,'MILLER',15,'20050630','20050703'),
(7934,'MILLER',9,'20050624','20050627'),
(7934,'MILLER',6,'20050621','20050623');

select * from emp_project

/*
The key here is to find rows where PROJ_START (the date the new project starts)
occurs on or after another project’s PROJ_START date and on or before that other
project’s PROJ_END date. To begin, you need to be able to compare each project with
each other project (for the same employee). By self-joining EMP_PROJECT on
employee, you generate every possible combination of two projects for each
employee. To find the overlaps, simply find the rows where PROJ_START for any
PROJ_ID falls between PROJ_START and PROJ_END for another PROJ_ID by the
same employee.

EMPNO ENAME
 MSG
----- ---------- --------------------------------
7782 CLARK project 7 overlaps project 4
7782 CLARK project 10 overlaps project 7
7782 CLARK project 13 overlaps project 10
7839 KING project 8 overlaps project 5
7839 KING project 5 overlaps project 2
7934 MILLER project 12 overlaps project 9
7934 MILLER project 6 overlaps project 3
*/

select
    a.empno, a.ename, 'project ' + cast(a.proj_id as varchar) + ' overlaps project ' + cast(b.proj_id as varchar) as msg
from
    emp_project a inner join emp_project b
    on a.empno = b.empno and a.proj_id > b.proj_id
where 
    a.proj_start BETWEEN b.proj_start AND b.proj_end
    or b.proj_start BETWEEN a.proj_start AND a.proj_end

--11.1 Paginating Through a Result Set
/*
You want to paginate or “scroll through” a result set. For example, you want to return
the first five salaries from table EMP, then the next five, and so forth. Your goal is to
allow a user to view five records at a time, scrolling forward with each click of a Next
button.
*/

select sal
from (
    select row_number() over (order by sal) as rn, sal
    from emp
) x
where rn between 1 and 5
go

select sal
from (
    select row_number() over (order by sal) as rn, sal
    from emp
) x
where rn between 6 and 10


select sal
from (
    select row_number() over (order by sal) as rn, sal
    from emp
) x
where rn between 11 and 15
go


select * from
(
    select row_number() over (order by sal) as rn, sal
    from emp
) x

-- 11.2 Skipping n Rows from a Table
/*
You want a query to return every other employee in table EMP; you want the first
employee, third employee, and so forth. For example, from the following result set:
ENAME
--------
ADAMS
ALLEN
BLAKE
CLARK
FORD
JAMES
JONES
KING
MARTIN
MILLER
SCOTT
SMITH
TURNER
WARD
you want to return the following:
ENAME
----------
ADAMS
BLAKE
FORD
JONES
MARTIN
SCOTT
TURNER
*/

select ename from 
(
    select ROW_NUMBER() over (order by emp.ename) as rn, emp.ename
    from emp
) x
where rn%2 = 1

-- 11.3 Incorporating OR Logic When Using Outer Joins

/*
You want to return the name and department information for all employees in
departments 10 and 20 along with department information for departments 30 and
40 (but no employee information).
*/

select * from emp;
go
select * from dept;
go

select e.ename, d.deptno, d.dname, d.loc
from
    emp e inner join dept d
    on e.deptno = d.deptno
where e.deptno = 10 or e.deptno = 20
union all
select 
    '' as ename, d.deptno, d.dname, d.loc
from dept d
where d.deptno = 30 or d.deptno = 40
order by 2
go


select * from emp
where emp.deptno = 30

select coalesce(e.ename,'') ename, d.deptno, d.dname, d.loc
from dept d left join emp e
on (d.deptno = e.deptno
    and (e.deptno = 10 or e.deptno = 20))
order by 2

-- 11.4 Determining Which Rows Are Reciprocals

drop view if exists V
create table V (test1 integer, test2 integer);

insert into V values
(20,20),
(50,25),
(20,20),
(60,30),
(70,90),
(80,130),
(90,70),
(100,50),
(110,55),
(120,60),
(130,80),
(140,70)

select * from V

select distinct v1.test1, v1.test2
from V v1 inner join V v2
    on v1.test1 = v2.test2 and v2.test1 = v1.test2 and v1.test1 <= v1.test2

--11-5 Selecting the Top n Records
/*
You want to limit a result set to a specific number of records based on a ranking of
some sort. For example, you want to return the names and salaries of the employees
with the top five salaries.
*/

select ename, sal
from (
    select ename, sal, dense_rank() over(order by sal desc) dr
    from emp
) x
where dr <= 5


-- 10.1 Locating a Range of Consecutive Values
select * from V;
drop table if exists V;

create table V 
(
    proj_id integer,
    proj_start date,
    proj_end date
);
insert into V values
(1,'20200101','20200102'),
(2,'20200102','20200103'),
(3,'20200103','20200104'),
(4,'20200104','20200105'),
(5,'20200106','20200107'),
(6,'20200116','20200117'),
(7,'20200117','20200118'),
(8,'20200118','20200119'),
(9,'20200119','20200120'),
(10,'20200121','20200122'),
(11,'20200126','20200127'),
(12,'20200127','20200128'),
(13,'20200128','20200129'),
(14,'20200129','20200130');


select * from V;

select proj_id, proj_start, proj_end
from 
(
    select proj_id, proj_start, proj_end,
        lead(proj_start) over (order by proj_id) next_proj_start
    from V
) allias
where next_proj_start = proj_end

-- 10.2 Finding Differences Between Rows in the Same Group or Partition
/*
You want to return the DEPTNO, ENAME, and SAL of each employee along with the
difference in SAL between employees in the same department (i.e., having the same
value for DEPTNO). The difference should be between each current employee and
the employee hired immediately afterward (you want to see if there is a correlation
between seniority and salary on a “per department” basis). For each employee hired
last in his department, return “N/A” for the difference. 
*/

with next_sal_tab (deptno, ename, sal, hiredate, next_sal) as
(
    select deptno, ename, sal, hiredate, lead(sal)
)

--11.1 Paginating Through a Result Set

select *
from
(
    select 
        row_number() over (order by sal desc) as rn, sal
    from emp
) x


-- 11.6 Finding Records with the Highest and Lowest Values

select ename
from
(
    select ename, sal, 
        min(sal) over () min_sal,
        max(sal) over () max_sal
    from emp
) x
where sal in (min_sal, max_sal)

-- 11.7 Investigating Future Rows
/*
You want to find any employees who earn less than the employee hired immediately
after them. Based on the following result set:
*/

select ename, sal, hiredate from
(
select ename, sal, hiredate, 
    lead(sal,1) over (order by hiredate) next_sal,
    lead(hiredate) over (order by hiredate) next_hire_date
from emp
) x
where sal < next_sal

go

select ename, sal, hiredate from 
(
    select ename, sal, hiredate, 
        lead(sal, cnt-rn+1) over (order by hiredate) next_sal
    from (
        select ename, sal, hiredate, 
            count(*) over (partition by hiredate) cnt,
            row_number() over (partition by hiredate order by empno) rn
        from emp
    ) tmp
) x
where sal < next_sal

-- 5.1 Listing Tables in a Schema
/*
You want to see a list of all the tables you’ve created in a given schema.
*/
use sql_cookbook;
select table_name
from information_schema.tables


--5.2 Listing a Table’s Columns
/*
You want to list the columns in a table, along with their data types, and their position
in the table they are in.
*/

select * from information_schema.columns

select column_name, data_type, ordinal_position
from information_schema.columns
where table_schema = 'dbo'
    and table_name = 'EMP'

--5.3 Listing Indexed Columns for a Table
/*
You want list indexes, their columns, and the column position (if available) in the
index for a given table.
*/
select
    a.name table_name,
    b.name index_name,
    d.name column_name,
    c.index_column_id
from
    sys.tables a,
    sys.indexes b,
    sys.index_columns c,
    sys.columns d
where a.object_id = b.object_id
    and b.object_id = c.object_id
    and b.index_id = c.index_id
    and c.object_id = d.object_id
    and c.column_id = d.column_id
    and a.name = 'EMP'


-- Drop the database 'DatabaseName'
-- Connect to the 'master' database to run this snippet
USE master
GO
-- Uncomment the ALTER DATABASE statement below to set the database to SINGLE_USER mode if the drop database command fails because the database is in use.
-- ALTER DATABASE DatabaseName SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
-- Drop the database if it exists
IF EXISTS (
    SELECT [name]
        FROM sys.databases
        WHERE [name] = N'TSQL70-461-761'
)
DROP DATABASE [TSQL70-461-761]
GO