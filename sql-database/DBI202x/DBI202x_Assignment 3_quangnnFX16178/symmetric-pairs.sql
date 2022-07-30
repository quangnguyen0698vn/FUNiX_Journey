SELECT distinct f1.x, f1.y
FROM Functions f1 inner join Functions f2
    on (f1.x = f2.y and f1.y = f2.x)
WHERE f1.x < f1.y or (f1.x = f1.y AND (SELECT count(*) FROM Functions func WHERE func.x = f1.x AND func.y = f1.y) >= 2)
--https://www.hackerrank.com/challenges/symmetric-pairs/
