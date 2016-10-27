/**
 * 
 */
package a00892244.lab7.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Edward Lambke A00892244
 *
 */
public class DataManager {
	Connection con = null;
	Statement stmt = null;
	ResultSet queryResults = null;

	/**
	 * 
	 */
	public DataManager() {

	}

	public DataManager(String driver, String url, String user, String password, String dbname) {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);


		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	public void close() {
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String query) throws SQLException  {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			queryResults = stmt.executeQuery(query);
			
			return queryResults;
	}
	
	public void test() throws SQLException {
		ResultSet results = executeQuery("asdfh");
		
		results.getMetaData();
		results.getMetaData().getColumnName(1);
		
	}

}
