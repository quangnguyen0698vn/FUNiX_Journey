SELECT TOP(1) total, count(*)
FROM (SELECT (SALARY * MONTHS) total FROM Employee) totalTable
GROUP BY total
ORDER BY total desc
--https://www.hackerrank.com/challenges/earnings-of-employees/problem
