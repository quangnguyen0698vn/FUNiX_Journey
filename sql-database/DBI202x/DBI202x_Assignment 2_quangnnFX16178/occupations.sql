SELECT MAX(doctor_name), MAX(professor_name), MAX(singer_name), MAX(actor_name)
FROM
(
    SELECT
    (SELECT count(*)+1
     FROM OCCUPATIONS occ2
     WHERE occ1.Occupation = occ2.Occupation AND occ2.Name < occ1.Name
    ) num_row,
    CASE WHEN Occupation = 'Doctor' THEN name END doctor_name,
    CASE WHEN Occupation = 'Professor' THEN name END professor_name,
    CASE WHEN Occupation = 'Singer' THEN name END singer_name,
    CASE WHEN Occupation = 'Actor' THEN name END actor_name
    FROM OCCUPATIONS occ1
) tmp
GROUP BY num_row
--https://www.hackerrank.com/challenges/occupations
