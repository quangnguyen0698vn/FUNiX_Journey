CREATE TABLE #Sales
(
       OrderDate DATE
	  ,OrderMonth DATE
      ,TotalDue MONEY
	  ,OrderRank INT
)

INSERT INTO #Sales
(
       OrderDate
	  ,OrderMonth
      ,TotalDue
	  ,OrderRank
)
SELECT 
       OrderDate
	  ,OrderMonth = DATEFROMPARTS(YEAR(OrderDate),MONTH(OrderDate),1)
      ,TotalDue
	  ,OrderRank = ROW_NUMBER() OVER(PARTITION BY DATEFROMPARTS(YEAR(OrderDate),MONTH(OrderDate),1) ORDER BY TotalDue DESC)

FROM AdventureWorks2019.Sales.SalesOrderHeader



CREATE TABLE #AvgSalesMinusTop10
(
OrderMonth DATE,
TotalSales MONEY
)

INSERT INTO #AvgSalesMinusTop10
(
OrderMonth,
TotalSales
)
SELECT
OrderMonth,
TotalSales = SUM(TotalDue)
FROM #Sales
WHERE OrderRank > 10
GROUP BY OrderMonth


CREATE TABLE #Purchases
(
       OrderDate DATE
	  ,OrderMonth DATE
      ,TotalDue MONEY
	  ,OrderRank INT
)

INSERT INTO #Purchases
(
       OrderDate
	  ,OrderMonth
      ,TotalDue
	  ,OrderRank
)
SELECT 
       OrderDate
	  ,OrderMonth = DATEFROMPARTS(YEAR(OrderDate),MONTH(OrderDate),1)
      ,TotalDue
	  ,OrderRank = ROW_NUMBER() OVER(PARTITION BY DATEFROMPARTS(YEAR(OrderDate),MONTH(OrderDate),1) ORDER BY TotalDue DESC)

FROM AdventureWorks2019.Purchasing.PurchaseOrderHeader



CREATE TABLE #AvgPurchaseMinusTop10
(
OrderMonth DATE,
TotalPurchases MONEY
)

INSERT INTO #AvgPurchaseMinusTop10
(
OrderMonth,
TotalPurchases
)
SELECT
OrderMonth,
TotalPurchases = SUM(TotalDue)
FROM #Purchases
WHERE OrderRank > 10
GROUP BY OrderMonth



SELECT
A.OrderMonth,
A.TotalSales,
B.TotalPurchases

FROM #AvgSalesMinusTop10 A
	JOIN #AvgPurchaseMinusTop10 B
		ON A.OrderMonth = B.OrderMonth

ORDER BY 1

DROP TABLE #Sales
DROP TABLE #AvgSalesMinusTop10
DROP TABLE #Purchases
DROP TABLE #AvgPurchaseMinusTop10