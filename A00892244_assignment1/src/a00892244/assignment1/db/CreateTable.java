package a00892244.assignment1.db;
// CreateTable.java
// Creates a test table with JDBC

import java.sql.*;
import java.util.*;
import java.io.*;

public class CreateTable {
	Connection con = null;
	Statement stmt = null;
	ResultSet queryResults = null;

	public CreateTable() {
		try {

			Properties props = new Properties();
			props.load(new FileInputStream("files" + File.separator
					+ "dbprops.properties"));

			Class.forName(props.getProperty("Driver"));
			con = DriverManager.getConnection(props.getProperty("URL"), props);

			stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY   );

			stmt.execute("IF OBJECT_ID('edsdb_') IS NOT NULL DROP TABLE edsdb_");

			String createTable = "CREATE TABLE edsdb_(id INT, lName CHAR(15), "
					+ "fName CHAR(15))";
			stmt.execute(createTable);

			String createIndex = "CREATE UNIQUE INDEX tableKey "
					+ "ON edsdb_(id)";
			stmt.execute(createIndex);

			String strInsert = "INSERT INTO edsdb_(id, lName, fName) "
					+ "VALUES(1, 'Lennon', 'John')";

			@SuppressWarnings("unused")
			int rowsAffected = stmt.executeUpdate(strInsert);

			strInsert = "INSERT INTO edsdb_(id, lName, fName) "
					+ "VALUES(2, 'McCartney', 'Paul')";
			rowsAffected = stmt.executeUpdate(strInsert);

			strInsert = "INSERT INTO edsdb_(id, lName, fName) "
					+ "VALUES(3, 'Harrison', 'George')";
			rowsAffected = stmt.executeUpdate(strInsert);

			strInsert = "INSERT INTO edsdb_(id, lName, fName) "
					+ "VALUES(4, 'Starr', 'Ringo')";
			rowsAffected = stmt.executeUpdate(strInsert);
			
			ResultSet results = stmt.executeQuery("SELECT * FROM edsdb_");
			 
			while (results.next()) { 
				System.out.println(results.getString(3));
			}

			System.out.println("yup");
			
			results.beforeFirst();
			
			while (results.next()) { 
				System.out.println(results.getString(3));
			}
			
			stmt.close();
			con.close();

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new CreateTable();
	}
}
