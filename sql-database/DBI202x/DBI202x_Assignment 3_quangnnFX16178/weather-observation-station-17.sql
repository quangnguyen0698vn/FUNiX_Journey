SELECT CONVERT(DECIMAL(10,4),LONG_W) FROM STATION WHERE LAT_N = (SELECT MIN(LAT_N) FROM STATION WHERE LAT_N > 38.7780)
--https://www.hackerrank.com/challenges/weather-observation-station-17/problem
