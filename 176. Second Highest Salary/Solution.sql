WITH salary_rnk AS (
    SELECT salary, DENSE_RANK() OVER (ORDER BY salary DESC) AS rnk
    FROM Employee
)
SELECT 
    (SELECT salary FROM salary_rnk WHERE rnk = 2 LIMIT 1) AS SecondHighestSalary;
