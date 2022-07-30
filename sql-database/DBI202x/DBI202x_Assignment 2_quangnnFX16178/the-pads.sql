SELECT Name + '(' + LEFT(Occupation,1) + ')' FROM OCCUPATIONS ORDER BY Name;
SELECT 'There are a total of ' + CONVERT(varchar(20),COUNT(*)) + ' ' + lower(Occupation) + 's.' FROM OCCUPATIONS GROUP BY Occupation ORDER BY COUNT(*), Occupation;
--https://www.hackerrank.com/challenges/the-pads/problem
