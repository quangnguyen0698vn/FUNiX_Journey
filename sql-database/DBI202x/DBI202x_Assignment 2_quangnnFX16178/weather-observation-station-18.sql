SELECT CONVERT(DECIMAL(10,4),ROUND(MAX(LAT_N) - MIN(LAT_N) + MAX(LONG_W) - MIN(LONG_W), 4)) FROM STATION
--https://www.hackerrank.com/challenges/weather-observation-station-18
