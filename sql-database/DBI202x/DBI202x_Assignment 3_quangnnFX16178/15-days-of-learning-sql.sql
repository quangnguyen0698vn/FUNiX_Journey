/*
Enter your query here.
Please append a semicolon ";" at the end of the query and enter your query in a single line to avoid error.
*/

DECLARE @startDate DATE = '20160301'
DECLARE @curMax int = 0
SELECT
    DISTINCT sub.submission_date,
    (SELECT count(hacker_id) FROM
        (
        SELECT sub2.hacker_id
        FROM SUBMISSIONS sub2
        WHERE sub2.submission_date <= sub.submission_date
        GROUP BY sub2.hacker_id
        HAVING count(distinct sub2.submission_date) = DATEDIFF(day, @startDate, sub.submission_date) + 1
        ) ans1
    ),
    (SELECT MIN(hacker_id) FROM
        (
        SELECT sub2.hacker_id
        FROM SUBMISSIONS sub2
        WHERE sub2.submission_date = sub.submission_date
        GROUP BY sub2.hacker_id
        HAVING count(sub2.submission_id) =
        (SELECT max(cnt) FROM
            (
                SELECT count(sub3.submission_id) cnt
                FROM SUBMISSIONS sub3
                WHERE sub3.submission_date = sub.submission_date
                GROUP BY sub3.hacker_id
            ) ans2
        )
        ) ans1
     ),
    (Select h.name from Hackers h where h.hacker_id =
     (SELECT MIN(hacker_id) FROM
        (
        SELECT sub2.hacker_id
        FROM SUBMISSIONS sub2
        WHERE sub2.submission_date = sub.submission_date
        GROUP BY sub2.hacker_id
        HAVING count(sub2.submission_id) =
        (SELECT max(cnt) FROM
            (
                SELECT count(sub3.submission_id) cnt
                FROM SUBMISSIONS sub3
                WHERE sub3.submission_date = sub.submission_date
                GROUP BY sub3.hacker_id
            ) ans2
        )
        ) ans1
     )
    )

FROM Submissions sub
ORDER BY 1

--https://www.hackerrank.com/challenges/15-days-of-learning-sql/problem
