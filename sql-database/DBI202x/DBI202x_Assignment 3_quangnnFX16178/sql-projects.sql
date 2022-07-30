WITH beginning as
(SELECT Start_Date from Projects WHERE Start_Date <> ALL (SELECT End_Date FROM Projects) )
SELECT
    bg1.Start_Date,
    (SELECT count(*)+1 from beginning bg2 where bg2.Start_Date < bg1.Start_Date) ranking
INTO result1
FROM beginning bg1;

WITH ending as
(SELECT End_Date from Projects WHERE End_Date <> ALL (SELECT Start_Date FROM Projects) )
SELECT
    ed1.End_Date,
    (SELECT count(*)+1 from ending ed2 where ed2.End_Date < ed1.End_Date) ranking
INTO result2
FROM ending ed1;

SELECT result1.Start_Date, result2.End_Date
FROM result1 inner join result2
    on result1.ranking = result2.ranking
ORDER BY DateDiff(day, result1.Start_Date, result2.End_Date), 1

--https://www.hackerrank.com/challenges/sql-projects/
