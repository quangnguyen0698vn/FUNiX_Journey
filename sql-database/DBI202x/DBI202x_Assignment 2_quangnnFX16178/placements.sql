SELECT stu.name
FROM Students stu
    INNER JOIN Friends frd
    ON stu.id = frd.id
    INNER JOIN Packages frdSal
    ON frd.Friend_ID = frdSal.ID
    INNER JOIN Packages stuSal
    on stu.ID = stuSal.id
WHERE frdSal.Salary > stuSal.salary
ORDER BY frdSal.Salary
--https://www.hackerrank.com/challenges/placements/
