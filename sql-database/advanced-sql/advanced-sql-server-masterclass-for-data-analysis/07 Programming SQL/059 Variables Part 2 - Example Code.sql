--Variables for complex date math:

DECLARE @Today DATE = CAST(GETDATE() AS DATE)

SELECT @Today

DECLARE @BOM DATE = DATEFROMPARTS(YEAR(@Today),MONTH(@Today),1)

SELECT @BOM 

DECLARE @PrevEOM DATE = DATEADD(DAY,-1,@BOM)

SELECT @PrevEOM

DECLARE @PrevBOM DATE = DATEADD(MONTH,-1,@BOM)

SELECT @PrevBOM



SELECT
*
FROM AdventureWorks2019.dbo.Calendar
WHERE DateValue BETWEEN @PrevBOM AND @PrevEOM






