SELECT hack.hacker_id, hack.name
FROM Submissions sub
    INNER JOIN Challenges chal
    ON sub.challenge_id = chal.challenge_id
    INNER JOIN Difficulty dif
    ON chal.difficulty_level = dif.difficulty_level
    INNER JOIN Hackers hack
    on sub.hacker_id = hack.hacker_id
WHERE sub.score = dif.score
GROUP BY hack.hacker_id, hack.name
HAVING count(*) >= 2
ORDER BY count(*) DESC, hacker_id
--https://www.hackerrank.com/challenges/full-score/problem
