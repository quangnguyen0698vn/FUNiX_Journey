SELECT AVG(POPULATION)
FROM CITY
WHERE DISTRICT = 'California'
GROUP BY DISTRICT
--https://www.hackerrank.com/challenges/revising-aggregations-the-average-function
