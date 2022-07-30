SELECT CASE
    WHEN (tri.a + tri.b <= tri.c) OR (tri.a + tri.c <= tri.b) OR (tri.b + tri.c <= tri.a) THEN "Not A Triangle"
    WHEN (tri.a = tri.b) AND (tri.b = tri.c) THEN "Equilateral"
    WHEN (tri.a = tri.b) OR (tri.a = tri.c) OR (tri.b = tri.c) THEN "Isosceles"
    ELSE "Scalene" END
FROM TRIANGLES tri;
--https://www.hackerrank.com/challenges/what-type-of-triangle/problem
