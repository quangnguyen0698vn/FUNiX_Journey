SELECT tmp1.id, tmp1.age, tmp1.coins_needed, tmp1.power
FROM
    (select wands.*, wp.age from wands inner join wands_property wp on wands.code = wp.code) tmp1
    inner join
    (
        SELECT wands.power, wp. age, MIN(coins_needed) as coins_needed
        FROM
            wands inner join wands_property wp
            on wands.code = wp.code
            where wp.is_evil = 0
        GROUP BY wands.power, wp.age
    ) tmp2
    on tmp1.power = tmp2.power and tmp1.age = tmp2.age and tmp1.coins_needed = tmp2.coins_needed
ORDER BY 4 desc, 2 desc
--https://www.hackerrank.com/challenges/harry-potter-and-wands/
