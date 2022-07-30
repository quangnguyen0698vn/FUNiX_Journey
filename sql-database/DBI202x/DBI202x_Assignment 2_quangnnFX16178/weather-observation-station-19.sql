SELECT CONVERT(DECIMAL(10,4),SQRT(POWER(MAX(LAT_N) - MIN(LAT_N),2)+POWER(MAX(LONG_W) - MIN(LONG_W),2))) FROM STATION
--https://www.hackerrank.com/challenges/weather-observation-station-19
