SELECT CEILING(AVG(SALARY*1.0) - AVG(1.0*CONVERT(INT,REPLACE(CONVERT(varchar(10), SALARY),'0','')))) FROM EMPLOYEES
--https://www.hackerrank.com/challenges/the-blunder
