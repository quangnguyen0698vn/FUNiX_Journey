SELECT
    CASE WHEN Grades.Grade >= 8 THEN Students.Name ELSE NULL END,
    Grades.Grade, Students.Marks
FROM Students inner join Grades
    ON Students.marks BETWEEN Grades.Min_Mark AND Grades.Max_Mark
ORDER BY 2 DESC, 1 ASC, 3 ASC
--https://www.hackerrank.com/challenges/the-report/
