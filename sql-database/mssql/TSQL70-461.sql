--Converting from dates to strings
select format(cast('2015-06-25 01:02:03.456' as datetime),'D','zh-CN') as MyFormattedInternationalLongDate

select format(cast('2015-06-25 01:59:03.456' as datetime),'dd-MM-yyyy') as MyFormattedBritishDate

select format(cast('2015-06-25 01:02:03.456' as datetime),'D') as MyFormattedLongDate

select format(cast('2015-06-25 01:02:03.456' as datetime),'d') as MyFormattedShortDate



select try_convert(date,'Thursday, 25 June 2015') as MyConvertedDate --NULL
SELECT parse('Thursday, 25 June 2015' as date) as MyParsedDate;
select parse('Jueves, 25 de junio de 2015' as date using 'es-ES') as MySpanishParsedDate


go

DECLARE @mydate as datetime = '2015-06-25 01:02:03.456';
-- SELECT 'The date and time is: ' + @mydate; NOT WORKING
go

DECLARE @mydate as datetime = '2015-06-25 01:02:03.456';
SELECT 'The date and time is: ' + convert(nvarchar(20), @mydate, 104) as myConvertedDate;;

go

DECLARE @mydate as datetime = '2015-06-25 01:02:03.456';
SELECT CAST(@mydate as nvarchar(20)) as MyCastDate;

go

--date offset
-- DECLARE @myDateOffset as datetimeoffset = '2015-06-25 01:02:03.456 + 05:30';
-- SELECT SWITCHOFFSET(@myDateOffset, '-05:00') as MyDateOffsetTexas;
-- GO
-- SELECT SYSDATETIMEOFFSET() TimeNowWithOffset;
-- SELECT SYSUTCDATETIME() as TimeNowUTC;


-- SELECT DATETIME2FROMPARTS(2015, 06, 25, 1, 2, 3, 456, 6);
-- SELECT DATETIMEOFFSETFROMPARTS(2015, 06, 25, 1, 2, 3, 456, 5, 30, 6);
-- DECLARE @myDateOffset as DATETIMEOFFSET(2) = '2015-06-25 01:02:03.456 +05:30';
-- SELECT @myDateOffset as MyDateOffset;
-- GO
-- DECLARE @myDate as datetime2 = '2015-06-25 01:02:03.456';
-- SELECT TODATETIMEOFFSET(@myDate, '+05:30') as MyDateOffset;
-- --SELECT 1234 + '567'; --'567' IS CONVERTED TO A NUMBER
-- --SELECT 1234 + 'is my number'; --ERROR
-- -- More date functions
-- SELECT DATEDIFF(SECOND, '2015-01-02 03:04:05', GETDATE()) as SecondsElapsed;
-- SELECT datename(WEEKDAY, getdate()) as myAnswer;
-- SELECT DATEPART(hour, '2015-01-02 03:04:05') myHour;
-- SELECT CURRENT_TIMESTAMP as rightnow;
-- SELECT GETDATE() as rightnow;
-- SELECT SYSDATETIME() as rightnow;
-- SELECT DATEADD(year, 1, '2015-01-02 03:04:05') as myYear;

-- Setting dates and date extraction
-- DECLARE @mydate as datetime = '2015-06-24 12:34:56.124';
-- SELECT @mydate as myDate;
-- SELECT YEAR(@mydate) as myYear, MONTH(@mydate) as myMonth, day(@mydate) as myDay;

-- DECLARE @mydate2 as datetime2(3) = '20150624 12:34:56.124';
-- SELECT @mydate2 as myDate2;

-- SELECT datefromparts(2015,06,24) as ThisDate;
-- SELECT DATETIME2FROMPARTS(2015,06,24,12,34,56,124,5) as ThatDate;

-- Joining a string to a number
-- SELECT 'My number is: ' + convert(varchar(20),4567);
-- SELECT 'My number is: ' + cast(4567 as varchar(20));

-- SELECT 'My salary is: $' + convert(varchar(20), 2345.6); -- works but not well
-- SELECT 'My salary is: ' + format(2345.6, 'C', 'fr-FR'); --C currency, 'fr-FR' France Culture
-- Joining two strings together
-- DECLARE @firstname as NVARCHAR(20)
-- DECLARE @middlename as NVARCHAR(20)
-- DECLARE @lastname as NVARCHAR(20);

-- SET @firstname = 'Sarah';
-- -- SET @middlename = 'Jane';
-- SET @lastname = 'Milligan';

-- -- SELECT @firstname + ' ' + @middlename + ' ' + @lastname as Fullname;
-- SELECT @firstname + IIF(@middlename is null, '', ' ' + @middlename)
-- + ' ' + @lastname as FullName;

-- SELECT @firstname + CASE WHEN @middlename is null THEN '' ELSE ' ' +
-- @middlename END + ' ' + @lastname as FullName;

-- SELECT @firstname + coalesce(' ' + @middlename, '') + ' ' + @lastname as FullName;

-- SELECT CONCAT(@firstname,' ' + @middlename,' ',@lastname) as FullName;

-- NULL - an introduction - RESULT SET ARE ALL NULL
-- DECLARE @myvar as int;
-- SELECT 1+1+1+1+1+@myvar+1+1 as myCOL; --NULL

-- DECLARE @myString as nvarchar(20);
-- SELECT datalength(@myString) as mystring;

-- DECLARE @mydecimal decimal(5,2);
-- SELECT TRY_CONVERT(decimal(5,2), 1000) myTryConvert;
-- SELECT TRY_CAST(1000 AS DECIMAL(5,2)) myTryCast;
-- STRINGS
-- char - ASCII - 1 byte
-- varchar - ASCII - 1 byte
-- nchar - UNICODE - 2 bytes
-- nvarchar - UNICODE - 2 bytes

-- Always prefix nchar and nvarchar values with N (capital N).

-- DECLARE @charASCII as varchar(10) = 'hellothere';
-- DECLARE @chrUNICODE as nvarchar(10) = N'helloϞ';
-- SELECT LEFT(@charASCII, 2) as myASCII, right(@chrUNICODE, 2) myUNICODE;
-- SELECT substring(@charASCII, 3, 2) as middleletters;
-- SELECT ltrim(rtrim(@charASCII)) as myTRIM;
-- SELECT replace(@charASCII, 'l', 'L') as myREPLACE;
-- SELECT upper(@charASCII) as myUPPER;
-- SELECT lower(@charASCII) as myLOWER;

-- DECLARE @chrMyCharacters as char(10);
-- set @chrMyCharacters = 'hello';
-- SELECT @chrMyCharacters myString, 
--     len(@chrMyCharacters) as myLength, 
--     datalength(@chrMyCharacters) as myDataLength;


-- --Converting between number and types
-- -- IMPLICIT

-- DECLARE @myvar as Decimal(5,2) = 3;
-- SELECT @myvar;
-- GO

-- -- EXPLICIT
-- SELECT CONVERT(Decimal(5,2),3)/2; --1.500000
-- SELECT CAST(3 as decimal(5,2))/2;

-- -- SELECT CONVERT(DECIMAL(5,2), 1000); ERRORS
-- SELECT 3/2;
-- SELECT 3/2.0;

-- SELECT CONVERT(INT, 12.345) + CONVERT(INT, 12.7);
-- SELECT CONVERT(INT, 12.345 + 12.7);

-- --Mathematical functions

-- SELECT RAND(365) myRand;
-- GO

-- SELECT PI() myPi;
-- SELECT EXP(1) e;

-- DECLARE @myvar as numeric(7,2) = -456;

-- SELECT ABS(@myvar) myAbs, SIGN(@myvar) as mySign

-- GO

-- DECLARE @myvar as numeric(7,2) = 3;

-- SELECT POWER(@myvar, 3) POWER;
-- SELECT SQUARE(@myvar) SQUARE;
-- SELECT POWER(@myvar, 0.5) POWER;
-- SELECT SQRT(@myvar) SQRT;

-- GO

-- DECLARE @myvar as numeric(7,2) = 12.345;

-- SELECT FLOOR(@myvar) FLOOR;
-- SELECT CEILING(@myvar) CEILING;
-- SELECT ROUND(@myvar, -1) AS myRound;
-- GO

-- --Creating temporary variables
-- DECLARE @myvar as int = 2;
-- SET @myvar = @myvar + 1;
-- SELECT @myvar myVariable;
-- GO
-- --Integer Numbers
-- DECLARE @myvar as SMALLINT = 2000;
-- SET @myvar = @myvar * 10;
-- SELECT @myvar AS myVariable;
-- GO
-- --None-integer numbers
-- DECLARE @myvar as numeric(7,2); -- or decimal(7,2) - 5 bytes needed
-- SET @myvar = 12345.67;
-- SELECT @myvar myVariable;
-- GO
-- DECLARE @myvar as decimal(18,8); --9 bytes needed
-- set @myvar = 1000000000.12345678
-- SELECT @myvar myVariable;
-- GO
-- DECLARE @myvar as SMALLMONEY = 123456.78917; --four-decimal-digit
-- SELECT @myvar as myVariable;
-- GO
-- DECLARE @myvar as float(24) = 123456.7891;
-- SELECT @myvar as myVariable;
-- GO

