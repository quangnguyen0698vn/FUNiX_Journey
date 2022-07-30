-- BEGIN TRANSACTION MyTransaction WITH MARK 'My Transaction'

-- SELECT * FROM [dbo].[tblEmployee]
-- UPDATE [dbo].[tblEmployee] set EmployeeNumber = 123 WHERE EmployeeNumber = 122

-- ROLLBACK TRAN MyTransaction
--COMMIT TRAN MyTransaction

-- UPDATE [dbo].[tblEmployee] set EmployeeNumber = 122 WHERE EmployeeNumber = 123

-- select * from [dbo].[tblEmployee]

-- select @@TRANCOUNT --0
-- begin tran  
--     select @@TRANCOUNT --1
--     begin tran
--         UPDATE [dbo].[tblEmployee] set EmployeeNumber = 123 WHERE EmployeeNumber = 122
--         select @@TRANCOUNT --2
--     commit tran
--     select @@TRANCOUNT --1 
-- if @@TRANCOUNT > 0 --yes
-- commit tran 
-- select @@TRANCOUNT --0


-- select * from [dbo].[tblEmployee]

--UPDATE [dbo].[tblEmployee] set EmployeeNumber = 122 WHERE EmployeeNumber = 123