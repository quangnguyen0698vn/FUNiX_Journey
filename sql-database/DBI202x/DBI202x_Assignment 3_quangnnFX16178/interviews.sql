WITH combined_table AS
(
    SELECT col.contest_id , chal.challenge_id
    FROM
    Colleges col inner join Challenges chal
    on col.college_id = chal.college_id
),
combined_sub as
(
    SELECT contest_id, total_submissions, total_accepted_submissions
    FROM combined_table cb inner join Submission_Stats ss
    on cb.challenge_id = ss.challenge_id
),
combined_view as
(
    SELECT contest_id, total_views, total_unique_views
    FROM combined_table cb inner join View_Stats vs
    on cb.challenge_id = vs.challenge_id
)
SELECT * FROM (
    SELECT con.contest_id, con.hacker_id, con.name,
        (SELECT SUM(cs.total_submissions) FROM combined_sub cs WHERE con.contest_id = cs.contest_id) as s1,
        (SELECT SUM(cs.total_accepted_submissions) FROM combined_sub cs WHERE con.contest_id = cs.contest_id) as s2,
        (SELECT SUM(cv.total_views) FROM combined_view cv WHERE con.contest_id = cv.contest_id) as s3,
        (SELECT SUM(cv.total_unique_views) FROM combined_view cv WHERE con.contest_id = cv.contest_id) as s4
    FROM Contests con
) tmp
WHERE COALESCE(tmp.s1+tmp.s2+tmp.s3+tmp.s4,0) > 0
ORDER BY 1
--https://www.hackerrank.com/challenges/interviews/
