--Adding additional columns
-- ALTER TABLE tblEmployee
-- ADD Department VARCHAR(10);

-- SELECT * FROM tblEmployee;

-- ALTER TABLE tblEmployee
-- ALTER COLUMN Department VARCHAR(20);

-- INSERT INTO tblEmployee
-- VALUES(132, 'Dylan', 'A', 'Word', 'HN513777D', '19920914', 'Customer Relations');

-- Insert rows into table 'TableName' in schema '[dbo]'
INSERT INTO [dbo].[tblEmployee]
( -- Columns to insert data into
 [EmployeeFirstName], [EmployeeMiddleName], [EmployeeLastName], [EmployeeGovernmentID], [DateOfBirth], [Department], [EmployeeNumber]
)
VALUES
( -- First row: values for the columns in the list above
 'Jossef', 'H', 'Wright', 'TX593671R', '19711224', 'Litigation', 131
);
GO

-- SELECT LEN('Customer Relations');
-- ALTER TABLE tblEmployee
-- DROP COLUMN Department;

-- ALTER TABLE tblEmployee
-- ADD Department VARCHAR(18);

SELECT * FROM tblEmployee;

--Create of tblEmployee table
-- sp_help tblEmployee;
-- CREATE TABLE tblEmployee 
-- (
--     EmployeeNumber INT NOT NULL,
--     EmployeeFirstName VARCHAR(50) NOT NULL,
--     EmployeeMiddleName VARCHAR(50) NULL,
--     EmployeeLastName VARCHAR(50) NOT NULL,
--     EmployeeGovernmentID CHAR(10) NULL,
--     DateOfBirth DATE NOT NULL
-- )