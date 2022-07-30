DECLARE @quantity INT = (SELECT count(*) FROM STATION)
DECLARE @middle INT = @quantity / 2;

SELECT DISTINCT LAT_N,
    (SELECT count(*) FROM STATION sta2 WHERE sta1.LAT_N >= sta2.LAT_N) as ranking
INTO frequency
FROM STATION sta1

DECLARE @left decimal(10,4) = (SELECT TOP(1) LAT_N FROM frequency WHERE ranking >= @middle ORDER BY ranking)

DECLARE @right decimal(10,4) = (SELECT TOP(1) LAT_N FROM frequency WHERE ranking >= @middle+1 ORDER BY ranking)

IF (@quantity%2 = 1)
    SELECT @right
ELSE
    SELECT (@left+@right)/2
--https://www.hackerrank.com/challenges/weather-observation-station-20
