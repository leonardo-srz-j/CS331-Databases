-------------------------------------------------Simple Queries
-- Query 1
-- PROVIDE A LIST OF JOB CANDIDATES WHO GOT HIRED ALONG WITH THEIR RESPECTIVE JOB TITLE AND HIRE DATE
USE AdventureWorks2014;

SELECT JC.JobCandidateID
	,E.JobTitle
	,E.HireDate
FROM HumanResources.Employee AS E
INNER JOIN HumanResources.JobCandidate AS JC ON E.BusinessEntityID = JC.BusinessEntityID;


-- Query 2
-- PROVIDE A LIST OF CITIES WITHT THE POSSIBLE DELIVERY METHODS
USE WideWorldImporters;

SELECT CA.CityName
	,DM.DeliveryMethodName
FROM Application.Cities_Archive AS CA
CROSS JOIN Application.DeliveryMethods_Archive AS DM


-- Query 3 
-- PROVIDE LIST OF LAST NAMES THAT INCLUDE AT LEAST ONE 1 "a"
USE TSQLV4;
SELECT lastname
	,firstname
FROM HR.Employees
WHERE LastName LIKE '%a%';

-- Query 4
-- PROVIDE A LIST OF PRODUCTS, THEIR PRICE AND THE CATEGORY THEY BELONG TO 
USE TSQLV4

SELECT P.productname AS Product
	, P.unitprice AS Price
	, C.categoryname AS Category
FROM Production.Products as P
	INNER JOIN Production.Categories AS C ON P.categoryid = C.categoryid

-- Query 5
-- PROVIDE A LIST OF AVAILABLE CURRENCY FORMS
USE AdventureWorksDW2014;

SELECT CurrencyKey
	,CurrencyName
FROM dbo.DimCurrency





--------------------------------------------------- Medium Queries
-- Query 1
-- provide me a list of all orders with a reason type that is not "other" 
use AdventureWorks2014;
select OH.CustomerID, OH.SalesOrderNumber,SR.ReasonType
from sales.SalesOrderHeader as OH
	 join sales.SalesOrderHeaderSalesReason as OHR
		on OH.SalesOrderID = OHR.SalesOrderID
	 join Sales.SalesReason as SR
		on (SR.SalesReasonID = OHR.SalesReasonID and not SR.ReasonType = 'other')

-- Query 2
-- make sure that every employee in the engineering department has a login ID
use AdventureWorks2014;
select E.LoginID, D.DepartmentID, D.[Name]
from HumanResources.Employee as E
	join HumanResources.EmployeeDepartmentHistory as DH
		on e.BusinessEntityID = DH.BusinessEntityID
	join HumanResources.Department as D
		on (d.DepartmentID = 1  and DH.DepartmentID = 1)
GROUP BY D.DepartmentID
	,LoginID
	,D.[Name]

-- Query 3
-- PROVIDE A LIST OF ALL TRANSACTIONS WITH UNKNOWN PAYMENT METHOD THAT OCCURED AT THE END OF THE MONTH IN 2016
USE WideWorldImportersDW;

SELECT T.[Transaction Key]
	,PM.[Payment Method]
	,T.[Date Key] AS DATE
FROM Fact.[Transaction] AS T
	INNER JOIN Dimension.[Payment Method] AS PM
	ON T.[Payment Method Key] = PM.[Payment Method Key] AND PM.[Payment Method] = 'Unknown'

WHERE T.[Date Key] = EOMONTH(T.[Date Key])
	AND YEAR(T.[Date Key]) = 2016
GROUP BY T.[Transaction Key]
	,PM.[Payment Method]
	,T.[Date Key]
ORDER BY T.[Date Key]



-- Query 4 ------------------------------------------WORSE
-- PROVIDE A LIST OF STOCK ITEMS SOLD
USE WideWorldImportersDW

SELECT DISTINCT DSI.[Stock Item]
	,Quantity AS QuantitySold
FROM Dimension.[Stock Item] AS DSI
INNER JOIN Fact.Sale AS FS ON DSI.[Stock Item Key] = FS.[Stock Item Key]
ORDER BY [Stock Item]

--FIXED
--IT NOW GROUPS THE SAME ITEMS AND ADDS THE QUANTITY SOLD
--IT GIVES 227 ROWS RATHER THAN 2265
--SELECT ELEMENTS ARE NOT AMBIGUOUS 
USE WideWorldImportersDW

SELECT DSI.[Stock Item Key]
	,DSI.[Stock Item]
	,SUM(FS.Quantity) AS QuantitySold
FROM Dimension.[Stock Item] AS DSI
INNER JOIN Fact.Sale AS FS ON FS.[Stock Item Key] = DSI.[Stock Item Key]
GROUP BY DSI.[Stock Item]
	,DSI.[Stock Item Key]


-- Query 5
-- PROVIDE A LIST THAT SHOWS IF ANY EMPLOYEE RATE CHANGED AT THE BEGINNING OF 2015
USE AdventureWorks2014
SELECT E.JobTitle
	,PH.RateChangeDate 
FROM HumanResources.Employee AS E
	LEFT JOIN HumanResources.EmployeePayHistory AS PH 
	ON PH.RateChangeDate = CAST('20150101'AS DATE)
GROUP BY E.JobTitle
	,PH.RateChangeDate


-- Query 6
-- COUNT THE NUMBER OF PEOPLE WITH THE SAME SCORE
USE TSQLV4;
SELECT
    COUNT(*) AS PeopleWithTheSameScore,
    S.score AS Score
FROM
    Stats.Tests AS T INNER JOIN Stats.Scores AS S 
		ON t.testid = s.testid
GROUP BY s.score


-- Query 7
-- FIND HOW MANY CUSTOMERS WE HAVE IN EACH GEOGRAPHY KEY
USE AdventureWorksDW2014;
SELECT
    COUNT(DISTINCT C.CustomerKey) AS #CUSTOMERS, 
	g.GeographyKey
FROM
    DimCustomer AS C INNER JOIN DimGeography AS G ON C.GeographyKey = G.GeographyKey
GROUP BY 	g.GeographyKey

-- Query 8
-- #OF ORDERS PER SHIPPING COMPANY
USE TSQLV4;
SELECT 
	COUNT( DISTINCT O.orderid) AS orders,
	companyname
FROM Sales.Shippers AS SP
	INNER JOIN Sales.Orders AS O
	ON SP.shipperid = O.shipperid
GROUP BY companyname


USE AdventureWorks2014
	SELECT 
	CAST(CardNumber AS bigint) AS number
	FROM Sales.CreditCard
	WHERE (CAST(CardNumber AS bigint) % 10000)  = 1254


--- query 9 
use WideWorldImporters
select top 200  OL.StockItemID, 
		OL.[Description],
		OL.OrderLineID,
		OL.PickingCompletedWhen,
		IL.LineProfit
from Sales.InvoiceLines as IL
inner join (select  StockItemID,
					[Description],
					OrderLineID,
					PickingCompletedWhen
			from Sales.OrderLines
			WHERE PickingCompletedWhen Between CAST('20160201' as DATE) and  CAST('20160301' as DATE)

		group by StockItemID,
				[Description],
				OrderLineID,
				PickingCompletedWhen
	
	)as OL

	on IL.StockItemID = OL.StockItemID
order by IL.LineProfit desc


--Query 10

use TSQLV4
select cu.custid,
		CU.companyname,
		CU.contacttitle,
		CONCAT(CU.city ,', ', CU.country) as CustomerRegion,
		SU.supplierid,
		SU.companyname,
		SU.contacttitle,
		CONCAT(SU.city ,', ', SU.country) as SupplierRegion
from sales.customers as CU
	inner join Production.Suppliers as SU
		on cu.country = SU.country and cu.city = su.city



-- QUERY 11
--PROVIDE A LIST OF EMPLOYEE WHOSE PAY RATE CHANGED BETWEEEN 2009 AND 2010
USE AdventureWorks2014
SELECT E.JobTitle
	,PH.RateChangeDate 
FROM HumanResources.Employee AS E
	 INNER JOIN HumanResources.EmployeePayHistory AS PH 
	ON e.BusinessEntityID = ph.BusinessEntityID

WHERE YEAR(PH.RateChangeDate) BETWEEN 2009 AND 2010
GROUP BY E.JobTitle
	,PH.RateChangeDate

--QUERY 12 
--PROVIDE ALL ETF PURCHASES BETWEEN 2013 AND 2016
USE WideWorldImportersDW;

SELECT T.[Transaction Key]
	,PM.[Payment Method]
	,T.[Date Key] AS DATE
FROM Fact.[Transaction] AS T
	INNER JOIN Dimension.[Payment Method] AS PM
	ON T.[Payment Method Key] = PM.[Payment Method Key] 

WHERE YEAR(T.[Date Key]) BETWEEN 2013 AND 2016
		AND PM.[Payment Method] = 'EFT'
GROUP BY T.[Transaction Key]
	,PM.[Payment Method]
	,T.[Date Key]
ORDER BY T.[Date Key]



























-- Complex Queries  


USE AdventureWorks2014

DROP FUNCTION

IF EXISTS dbo.LastFourEquals;
GO
CREATE FUNCTION dbo.LastFourEquals
(
	@LastFour int
)

RETURNS TABLE 
AS 
RETURN
	SELECT 
	CAST(CardNumber AS bigint) AS number
	FROM Sales.CreditCard
	WHERE (CAST(CardNumber AS bigint) % 10000)  = @LastFour
	 


-- Query 1
-- return list of credit cards that match given last numbers
USE AdventureWorks2014
SELECT number as CreditCardNumber
--	,LF.ExpMonth
	--,LF.CardType
FROM dbo.LastFourEquals(1254) as number
	INNER JOIN Sales.CreditCard AS CR ON CAST(CR.CardNumber as bigint) = number

GROUP BY number



































---------Functions
DROP FUNCTION

IF EXISTS dbo.GetAge;
GO
	CREATE FUNCTION dbo.GetAge (
		@birthdate AS DATE
		,@eventdate AS DATE
		) RETURNS INT
	AS
	BEGIN
		RETURN DATEDIFF(year, @birthdate, @eventdate) - CASE 
				WHEN 100 * MONTH(@eventdate) + DAY(@eventdate) < 100 * MONTH(@birthdate) + DAY(@birthdate)
					THEN 1
				ELSE 0
				END;
	END;
GO




use TSQLV4
IF OBJECT_ID('dbo.TopOrders') IS NOT NULL
	DROP FUNCTION dbo.TopOrders;
GO


CREATE FUNCTION dbo.TopOrders (@custid AS INT ,@n AS INT)
RETURNS TABLE
AS
RETURN

SELECT TOP (@n) orderid
	,empid
	,orderdate
	,requireddate
FROM Sales.Orders
WHERE custid = @custid
ORDER BY orderdate DESC
	,orderid DESC;
GO