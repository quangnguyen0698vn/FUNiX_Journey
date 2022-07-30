WITH Combined_table as (SELECT hacker_id, count(*) total FROM Challenges GROUP BY hacker_id)
SELECT h.hacker_id, h.name, ct.total
FROM  Combined_table ct inner join Hackers h on h.hacker_id = ct.hacker_id
WHERE ct.total = (SELECT MAX(Combined_table.total) FROM Combined_table)
    OR (SELECT COUNT(*) FROM Combined_table WHERE ct.total = Combined_table.total) = 1
ORDER BY 3 DESC, 1
--https://www.hackerrank.com/challenges/challenges/
