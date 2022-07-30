SELECT h.hacker_id, h.name, tmp2.total
FROM
    (SELECT tmp.hacker_id, sum(tmp.score) total
    FROM (
        SELECT sub.hacker_id, sub.challenge_id, max(sub.score) score
        FROM Submissions sub
        GROUP BY sub.hacker_id, sub.challenge_id
        ) tmp
    GROUP BY tmp.hacker_id) tmp2
    inner join Hackers h
    on h.hacker_id = tmp2.hacker_id
WHERE tmp2.total > 0
ORDER BY 3 DESC, 1
--https://www.hackerrank.com/challenges/contest-leaderboard/
