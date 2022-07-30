create database testing;
go
use testing;

drop table people;

create table people(
    id int not null IDENTITY,
    firstname varchar(20),
    lastname varchar(20),
    primary key (id)
)

insert into people values
('Tim', 'Corey'),
('Jon', 'Corey'),
('Chris', 'Corey'),
('Bill', 'Smith'),
('Jane', 'Smith'),
('Jamie', 'Smith'),
('Maggy', 'Corey'),
('Sue', 'Storm');
go
select * from people


create procedure dbo.spPeople_GetAll 
as 
begin
    -- set nocount on;
    select id, firstname, lastname
    from dbo.people;
end

exec dbo.spPeople_GetAll


alter procedure dbo.spPeople_GetAll 
as 
begin
    set nocount on;
    
    select id, firstname + ' '+ lastname as fullname
    from dbo.people;
end

exec dbo.spPeople_GetAll

--

create procedure dbo.spPeople_GetByLastName
    @lastname nvarchar(20)
as
begin
    select id, firstname, lastname
    from dbo.People
    where lastname = @lastname
end

exec dbo.spPeople_GetByLastName @lastname = 'Corey'
exec dbo.spPeople_GetByLastName 'Corey'

create procedure dbo.spGetByLastAndFirstname
    @lastname varchar(20),
    @firstname varchar(20)
as 
begin
    select id, firstname, lastname
    from dbo.people
    where lastname = @lastname and firstname = @firstname;
end

exec dbo.spGetByLastAndFirstname 'Corey', 'Tim'

-- create role  dbStoredProcedureOnlyAccess
-- -- drop role dbStoreProcedureOnlyAccess

-- grant execute to dbStoredProcedureOnlyAccess

-- alter procedure dbo.spTestFunixQuiz
-- as
-- begin
--    --insert into tblTransaction2
-- 	select *, 'Inserted' from Inserted
-- 	--insert into tblTransaction2
-- 	select *, 'Deleted' from Deleted
-- end

-- exec dbo.spTestFunixQuiz

-- use master;
-- create user readonlyuser for login readonlyuser

-- CREATE LOGIN readonlylogin   
--    WITH PASSWORD = 'password' MUST_CHANGE,  
--    CREDENTIAL = RestrictedFaculty;  
-- GO  