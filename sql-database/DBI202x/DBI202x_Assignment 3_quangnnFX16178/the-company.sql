SELECT
    company_code,
    founder,
    (SELECT count(DISTINCT lead.lead_manager_code) FROM Lead_Manager lead WHERE com.company_code = lead.company_code) leads,
    (SELECT count(DISTINCT sen.senior_manager_code) FROM Senior_Manager sen WHERE com.company_code = sen.company_code) sens,
    (SELECT count(DISTINCT man.manager_code) FROM Manager man WHERE com.company_code = man.company_code) mans,
    (SELECT count(DISTINCT emp.employee_code) FROM Employee emp WHERE com.company_code = emp.company_code) emps
FROM Company com
ORDER BY 1
--https://www.hackerrank.com/challenges/the-company/
