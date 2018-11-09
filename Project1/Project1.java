import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Project1 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;

		try {
			// searching for the JDBC driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			System.out.println("Connecting to database...");

			// get Connection function takes the URL connecting to the database, it uses
			// integrated security to login with windows authentication
			conn = DriverManager.getConnection("jdbc:sqlserver://occam.cs.qc.cuny.edu:21433;user=student;password=dbclass331");
			System.out.println("Connection succesful...");
			System.out.println("=========================SIMPLE1======================================");

			// executing query, you create a statement in the connection
			System.out.println("Executing Query");
			stmt = conn.createStatement();

			// your statement is the SQL query
			String sql = "USE TSQLV4\r\n" + "\r\n" + "SELECT productname\r\n" + "\r\n"
					+ "    ,categoryname\r\n" + "\r\n" + "    ,unitprice\r\n" + "\r\n"
					+ "FROM Production.Categories AS PC\r\n" + "\r\n"
					+ "INNER JOIN Production.Products AS PP ON PC.categoryid = PP.categoryid;";

			// you pull the result set from the statement
			ResultSet rs = stmt.executeQuery(sql);

			// extract data from result set
			while (rs.next()) {
				// retrieve column name
				String Product = rs.getString("productname");
				int Price = rs.getInt("unitprice");
				String Category = rs.getString("categoryname");

				System.out.print("Product: " + Product + "\t || ");
				System.out.print("Price: " + Price + "\t || ");
				System.out.println("\t  Category: " + Category);

			}
			// CLean-up environment

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("===============================================================");
		System.out.println("Connection Closed!");

	
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:sqlserver://occam.cs.qc.cuny.edu:21433;user=student;password=dbclass331");
			System.out.println("Connection succesful...");
			System.out.println("===========================SIMPLE2====================================");

			// executing query
			System.out.println("Executing Query");
			stmt = conn.createStatement();
			String sql = "USE TSQLV4​\r\n" + "\r\n" + "SELECT productname​\r\n" + "\r\n"
					+ "    ,categoryname​\r\n" + "\r\n" + "    ,unitprice​\r\n" + "\r\n"
					+ "FROM Production.Categories AS PC​\r\n" + "\r\n"
					+ "INNER JOIN Production.Products AS PP ON PC.categoryid = PP.categoryid;";
			ResultSet rs = stmt.executeQuery(sql);

			// extract data from result set
			while (rs.next()) {
				// retrieve column name
				String productname = rs.getString("productname");
				String categoryname = rs.getString("categoryname");
				String unitprice = rs.getString("unitprice");

				System.out.print("Productname: " + productname + "\t || ");
				System.out.print("Categoryname: " + categoryname + "\t || ");
				System.out.println("\t  Unitprice: " + unitprice);

			}

			// Step 6: CLean-up environment

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("===============================================================");
		System.out.println("Connection Closed!");

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:sqlserver://occam.cs.qc.cuny.edu:21433;user=student;password=dbclass331");
			System.out.println("Connection succesful...");
			System.out.println("=============================SIMPLE3==================================");

			// executing query
			System.out.println("Executing Query");
			stmt = conn.createStatement();
			String sql = "use AdventureWorksDW2014\r\n"
					+ "SELECT C.CustomerKey, C.EnglishOccupation, c.YearlyIncome,​\r\n" + "\r\n"
					+ "        g.GeographyKey, G.EnglishCountryRegionName​\r\n" + "\r\n"
					+ "FROM dbo.DimCustomer as C​\r\n" + "\r\n" + "     JOIN dbo.DimGeography AS G​\r\n" + "\r\n"
					+ "    ON C.GeographyKey = G.GeographyKey​\r\n" + "\r\n" + "Order By c.YearlyIncome desc ;";
			ResultSet rs = stmt.executeQuery(sql);

			// extract data from result set
			while (rs.next()) {
				// retrieve column name
				String CustomerKey = rs.getString("CustomerKey");
				String EnglishOccupation = rs.getString("EnglishOccupation");
				String YearlyIncome = rs.getString("YearlyIncome");
				String GeographyKey = rs.getString("GeographyKey");
				String EnglishCountryRegionName = rs.getString("EnglishCountryRegionName");

				System.out.print("CustomerKey: " + CustomerKey + "\t || ");
				System.out.print("EnglishOccupation: " + EnglishOccupation + "\t || ");
				System.out.print("YearlyIncome: " + YearlyIncome + "\t || ");
				System.out.print("GeographyKey: " + GeographyKey + "\t || ");
				System.out.println("\t  EnglishCountryRegionName: " + EnglishCountryRegionName);

			}

			// Step 6: CLean-up environment

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("===============================================================");
		System.out.println("Connection Closed!");

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:sqlserver://occam.cs.qc.cuny.edu:21433;user=student;password=dbclass331");
			System.out.println("Connection succesful...");
			System.out.println("=============================MEDIUM1==================================");

			// executing query
			System.out.println("Executing Query");
			stmt = conn.createStatement();
			String sql = "USE WideWorldImportersDW;\r\n" + "\r\n" + "SELECT T.[Transaction Key]\r\n"
					+ "	,PM.[Payment Method]\r\n" + "	,T.[Date Key] AS DATE\r\n" + "FROM Fact.[Transaction] AS T\r\n"
					+ "	INNER JOIN Dimension.[Payment Method] AS PM\r\n"
					+ "	ON T.[Payment Method Key] = PM.[Payment Method Key] AND PM.[Payment Method] = 'Unknown'\r\n"
					+ "\r\n" + "WHERE T.[Date Key] = EOMONTH(T.[Date Key])\r\n" + "	AND YEAR(T.[Date Key]) = 2016\r\n"
					+ "GROUP BY T.[Transaction Key]\r\n" + "	,PM.[Payment Method]\r\n" + "	,T.[Date Key]\r\n"
					+ "ORDER BY T.[Date Key]";
			ResultSet rs = stmt.executeQuery(sql);

			// extract data from result set
			while (rs.next()) {
				// retrieve column name
				String TransactionKey = rs.getString("Transaction Key");
				String PaymentMethod = rs.getString("Payment Method");
				String DATE = rs.getString("DATE");

				System.out.print("Transaction Key: " + TransactionKey + "\t || ");
				System.out.print("Payment Method: " + PaymentMethod + "\t || ");
				System.out.println("\t  DATE: " + DATE);

			}

			// Step 6: CLean-up environment

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("===============================================================");
		System.out.println("Connection Closed!");

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:sqlserver://occam.cs.qc.cuny.edu:21433;user=student;password=dbclass331");
			System.out.println("Connection succesful...");
			System.out.println("=============================MEDIUM2==================================");

			// executing query
			System.out.println("Executing Query");
			stmt = conn.createStatement();
			String sql = "USE WideWorldImporters​\r\n" + "\r\n" + "SELECT PS.SupplierID​\r\n" + "\r\n"
					+ "    ,SupplierName​\r\n" + "\r\n" + "    ,COUNT(*) AS OrdersFromSupplier​\r\n" + "\r\n"
					+ "    ,SUM(TransactionAmount) AS PaidToSupplier​\r\n" + "\r\n"
					+ "FROM Purchasing.SupplierTransactions AS PST​\r\n" + "\r\n"
					+ "INNER JOIN Purchasing.Suppliers AS PS ON PST.SupplierID = PS.SupplierID​\r\n" + "\r\n"
					+ "    AND PST.PurchaseOrderID IS NOT NULL​\r\n" + "\r\n" + "GROUP BY PS.SupplierID​\r\n"
					+ "\r\n" + "    ,SupplierName​\r\n" + "\r\n" + "ORDER BY PS.SupplierID;";
			ResultSet rs = stmt.executeQuery(sql);

			// extract data from result set
			while (rs.next()) {
				// retrieve column name
				String SupplierID = rs.getString("SupplierID");
				String SupplierName = rs.getString("SupplierName");
				String OrdersFromSupplier = rs.getString("OrdersFromSupplier");
				String PaidToSupplier = rs.getString("PaidToSupplier");

				System.out.print("SupplierID: " + SupplierID + "\t || ");
				System.out.print("SupplierName: " + SupplierName + "\t || ");
				System.out.print("OrdersFromSupplier: " + OrdersFromSupplier + "\t || ");
				System.out.println("\t  PaidToSupplier: " + PaidToSupplier);

			}

			// Step 6: CLean-up environment

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("===============================================================");
		System.out.println("Connection Closed!");

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:sqlserver://occam.cs.qc.cuny.edu:21433;user=student;password=dbclass331");
			System.out.println("Connection succesful...");
			System.out.println("================================MEDIUM3===============================");

			// executing query
			System.out.println("Executing Query");
			stmt = conn.createStatement();
			String sql = "USE AdventureWorksDW2014;​\r\n" + "\r\n" + "SELECT​\r\n" + "\r\n"
					+ "   COUNT(DISTINCT P.CustomerKey) AS CustomerQTY,​\r\n" + "\r\n" + "   MaritalStatus​\r\n"
					+ "\r\n" + "FROM DimCustomer AS P​\r\n" + "\r\n" + " INNER JOIN DimGeography AS G​\r\n" + "\r\n"
					+ " ON  P.GeographyKey = G.GeographyKey​\r\n" + "\r\n" + "GROUP BY P.MaritalStatus;";
			ResultSet rs = stmt.executeQuery(sql);

			// extract data from result set
			while (rs.next()) {
				// retrieve column name
				String CustomerQTY = rs.getString("CustomerQTY");
				String MaritalStatus = rs.getString("MaritalStatus");

				System.out.print("CustomerQTY: " + CustomerQTY + "\t || ");
				System.out.println("\t  MaritalStatus: " + MaritalStatus);

			}

			// Step 6: CLean-up environment

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("===============================================================");
		System.out.println("Connection Closed!");

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:sqlserver://occam.cs.qc.cuny.edu:21433;user=student;password=dbclass331");
			System.out.println("Connection succesful...");
			System.out.println("=============================COMPLEX1==================================");

			// executing query
			System.out.println("Executing Query");
			stmt = conn.createStatement();
			String sql = "USE AdventureWorks2014\r\n" + "SELECT HRD.DepartmentID\r\n" + "	,HRD.Name\r\n"
					+ "	,COUNT(*) AS NumEmployees\r\n"
					+ "	,SUM(dbo.GetCurrentSalary(BusinessEntityID)) AS TotalSalary\r\n"
					+ "	,SUM(dbo.GetCurrentSalary(BusinessEntityID)) / COUNT(*) AS SalPerEmployee\r\n"
					+ "FROM HumanResources.EmployeeDepartmentHistory AS HEDH\r\n"
					+ "INNER JOIN HumanResources.Department AS HRD ON HEDH.DepartmentID = HRD.DepartmentID\r\n"
					+ "GROUP BY HRD.DepartmentID, HRD.Name\r\n" + "ORDER BY NumEmployees DESC;";
			ResultSet rs = stmt.executeQuery(sql);

			// extract data from result set
			while (rs.next()) {
				// retrieve column name
				String DepartmentID = rs.getString("DepartmentID");
				String Name = rs.getString("Name");
				String NumEmployees = rs.getString("NumEmployees");
				String TotalSalary = rs.getString("TotalSalary");
				String SalPerEmployee = rs.getString("SalPerEmployee");

				System.out.print("DepartmentID: " + DepartmentID + "\t || ");
				System.out.print("Name: " + Name + "\t || ");
				System.out.print("NumEmployees: " + NumEmployees + "\t || ");
				System.out.print("TotalSalary: " + TotalSalary + "\t || ");
				System.out.println("\t  SalPerEmployee: " + SalPerEmployee);

			}

			// Step 6: CLean-up environment

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("===============================================================");
		System.out.println("Connection Closed!");

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:sqlserver://occam.cs.qc.cuny.edu:21433;user=student;password=dbclass331");
			System.out.println("Connection succesful...");
			System.out.println("===============================COMPLEX2================================");

			// executing query
			System.out.println("Executing Query");
			stmt = conn.createStatement();
			String sql = "USE AdventureWorks2014\r\n" + "SELECT number as CreditCardNumber\r\n"
					+ "--	,LF.ExpMonth\r\n" + "	--,LF.CardType\r\n" + "FROM dbo.LastFourEquals(1254) as number\r\n"
					+ "	INNER JOIN Sales.CreditCard AS CR ON CAST(CR.CardNumber as bigint) = number\r\n" + "\r\n"
					+ "GROUP BY number";
			ResultSet rs = stmt.executeQuery(sql);

			// extract data from result set
			while (rs.next()) {
				// retrieve column name
				String CreditCardNumber = rs.getString("CreditCardNumber");

				System.out.println("CreditCardNumber: " + CreditCardNumber);

			}

			// Step 6: CLean-up environment

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("===============================================================");
		System.out.println("Connection Closed!");

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:sqlserver://occam.cs.qc.cuny.edu:21433;user=student;password=dbclass331");
			System.out.println("Connection succesful...");
			System.out.println("================================COMPLEX3===============================");

			// executing query
			System.out.println("Executing Query");
			stmt = conn.createStatement();
			String sql = "use AdventureWorksDW2014\r\n"
					+ "select ProductInventory.ProductKey,SUM([dbo].[fnTotalProductionCost](ResellerSales.TotalProductCost)) as TotalProductCost\r\n"
					+ "FROM dbo.FactResellerSales as ResellerSales \r\n"
					+ "    INNER join dbo.FactProductInventory as ProductInventory \r\n"
					+ "on ResellerSales.ProductKey = ProductInventory.ProductKey \r\n"
					+ "  INNER join dbo.FactInternetSales as InternetSales\r\n"
					+ " on ProductInventory.ProductKey = InternetSales.ProductKey\r\n"
					+ "group by ProductInventory.ProductKey";
			ResultSet rs = stmt.executeQuery(sql);

			// extract data from result set
			while (rs.next()) {
				// retrieve column name
				String ProductKey = rs.getString("ProductKey");
				String TotalProductCost = rs.getString("TotalProductCost");

				System.out.print("ProductKey: " + ProductKey + "\t || ");
				System.out.println("\t  TotalProductCost: " + TotalProductCost);

			}

			// Step 6: CLean-up environment

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("===============================================================");
		System.out.println("Connection Closed!");
	}
		
		
		
		
		
		
	}

