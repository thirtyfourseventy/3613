/**
 * 
 */
package a00892244.assignment1.data;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author elambke
 *
 */
public class DataManager {
	Connection con = null;
	Statement stmt = null;
	ResultSet queryResults = null;
	String tableName = null;

	/**
	 * 
	 */
	public DataManager() {
		try {

			System.out.println(new File(".").getAbsolutePath());

			Properties props = new Properties();
			props.load(new FileInputStream("files" + File.separator + "a00892244dbprops.properties"));

			Class.forName(props.getProperty("Driver"));
			con = DriverManager.getConnection(props.getProperty("URL"), props);

			tableName = props.getProperty("Table");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void createTable() {
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			stmt.execute("IF OBJECT_ID('" + tableName + "') IS NOT NULL DROP TABLE " + tableName);

			String createTable = "CREATE TABLE " + tableName + "(number INT, name CHAR(64), "
					+ "brew_date CHAR(64), grist CHAR(512), hops CHAR(512), water CHAR(64), yeast CHAR(64), "
					+ "yeast_code CHAR(64), pitching_temp CHAR(64), ferment_temp CHAR(64), og DECIMAL(16,3), "
					+ "fg DECIMAL(16,3), abv DECIMAL(16,3), package_date CHAR(64), notes CHAR(64)) ";

			stmt.execute(createTable);

			String createIndex = "CREATE UNIQUE INDEX tableKey " + "ON " + tableName + " (number)";
			stmt.execute(createIndex);

			String strInsert = "INSERT INTO " + tableName + "(number, name, brew_date, grist, hops, water, "
					+ "yeast, yeast_code, pitching_temp, ferment_temp, og) "
					+ "VALUES(117,'Old Course Ale','10/08/2016','8lb 10oz Simpsons GP, 4oz Simpsons C-120, 2oz TF Black', "
					+ "'1oz 2016 Hallertau@FW','1tsp CaCl, .5tsp CaSO4','S-04','16908 239 0837 08 2017','12C','16C',1.042)";

			@SuppressWarnings("unused")
			int rowsAffected = stmt.executeUpdate(strInsert);

			strInsert = "INSERT INTO " + tableName + "(number, name, brew_date, grist, hops, water, "
					+ "yeast, yeast_code, pitching_temp, ferment_temp, og, fg, abv, package_date, notes) "
					+ "VALUES(116,'HarvestGoblin','09-08-2016','9lb TF GP,13oz Bairds C75,3oz Carafa Sp 2,1.5oz Black Strap Mollases', "
					+ "'1oz 4%AA EKG @ FW, 2.75oz Wet 2016 Hallertau + Goldings, 2oz dry 2016 Saaz @ 0, 1oz dry 2016 Cascade @ 0', "
					+ "'1tsp CaCl, 1tsp CaSO4', 'S-04','repitch from 115','12C','18C',1.044,1.012,4.289,'09-19-2016', '14g tubinado,1gal hobgoblin cask')";
			rowsAffected = stmt.executeUpdate(strInsert);

			strInsert = "INSERT INTO " + tableName + "(number, name, brew_date, grist, hops, water, "
					+ "yeast, yeast_code, pitching_temp, ferment_temp, og, fg, abv, package_date, notes) "
					+ "VALUES(115,'BBQ Bitter','08-21-2016','5lb TF GP, 2lb Boh Pils, 8oz Bairds C-75, 4oz TF Brown,5oz Homemade invert 3', "
					+ "'1oz 2015 Hallertau @ FW, 1oz @ 0','1tsp CaCl, 1tsp CaSO4','S-04','16908 239 0837 08 2017','14C','18C',1.036,1.012, "
					+ "3.217,'8-27-2016','1gal hobgoblin cask, 4.5gal in corny')";
			rowsAffected = stmt.executeUpdate(strInsert);

			strInsert = "INSERT INTO " + tableName + "(number, name, brew_date, grist, hops, water, "
					+ "yeast, yeast_code, ferment_temp, og, fg, abv, package_date) "
					+ "VALUES(114,'Homegrown IPA No Chill','07-30-2016','13lb Canadian 2-row, .5lb dextrose',"
					+ "'2013 Willamette 4oz @ FW, 2012 Willamette 3oz @ 45min, 2012 Cascade/Willamette blend 2oz @ 30min,"
					+ "2013 Willamette 2oz @ 15min, 2013 Willamette 3oz in Chill Keg','1tsp CaSO4','US-05','repitch from #113',"
					+ "'18C',1.060,1.009,6.836,'08-20-2016')";
			rowsAffected = stmt.executeUpdate(strInsert);

			strInsert = "INSERT INTO " + tableName + "(number, name, brew_date, grist, hops, water, "
					+ "yeast, yeast_code, pitching_temp, ferment_temp, og, fg, abv, package_date, notes) "
					+ "VALUES(113,'Black Beer thing','07-15-2016','10lbs Weyermann Bohemian, 6oz Carafa sp 2',"
					+ "'2015 Homegrown Hallertau: 1oz @ FW, 1oz in chill keg, Store bought Saaz 1oz 4.5%AA @ FW','',"
					+ "'US-05','11224 022 1717 01 2017','','18C','1.048',1.012,4.825737265,'07-30-2016','')";
			rowsAffected = stmt.executeUpdate(strInsert);

			ResultSet results = stmt.executeQuery("SELECT * FROM " + tableName);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void updateableRS() {
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
												ResultSet.CONCUR_UPDATABLE);
			
			String select = "SELECT * FROM " + tableName;
			queryResults = stmt.executeQuery (select);

			/*String insert = "INSERT INTO musicians_xyz(id, lName, fName) " +
						"VALUES(5, 'Bach', 'J.S.')";
			int rowsAffected = stmt.executeUpdate(insert);*/
			
//			queryResults.moveToInsertRow();
//			queryResults.updateInt("id", 5);
//			queryResults.updateString("lname", "Bach");
//			queryResults.updateString("fname", "J.S.");
//			queryResults.insertRow();
//			queryResults.moveToCurrentRow();

//
//			select = "SELECT * FROM musicians_xyz";
//			queryResults = stmt.executeQuery (select);

			display();

//			String delete= "DELETE FROM musicians_xyz WHERE id=5";
//			int rowsAffected = stmt.executeUpdate(delete);
//
//			select = "SELECT * FROM musicians_xyz";
//			queryResults = stmt.executeQuery (select);
//			
//			display();


		} catch(SQLException ex) {
		ex.printStackTrace();
		} catch(Exception ex) {
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

	public void display()
	{
		try {
			while (queryResults.next()) 
			{
				System.out.print( queryResults.getInt ("number"));
				System.out.print( ". " + queryResults.getString("name").trim());
				System.out.println( " " + queryResults.getString("og").trim());
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		System.out.println("\n");
	}
	
	public List<BrewingRecord> getAll() {
		ArrayList<BrewingRecord> allRecords = new ArrayList<BrewingRecord>();
		String select = "SELECT * FROM " + tableName;
		
		
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			queryResults = stmt.executeQuery (select);
			while (queryResults.next()){
				BrewingRecord record = new BrewingRecord(
						queryResults.getInt("number"), queryResults.getString("name"), queryResults.getString("brew_date"),
						queryResults.getString("grist"), queryResults.getString("hops"), queryResults.getString("water"),
						queryResults.getString("yeast"), queryResults.getString("yeast_code"), queryResults.getString("pitching_temp"),
						queryResults.getString("ferment_temp"), queryResults.getDouble("og"), queryResults.getDouble("fg"), queryResults.getDouble("abv"),
						queryResults.getString("package_date"), queryResults.getString("notes"));
				allRecords.add(record);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allRecords;
	}

	public static void main(String[] args) 
	{
		DataManager db = new DataManager();
		
//		db.createTable();
//		db.updateableRS();

		System.out.println(db.getAll().toString());
		
		
		
		
		db.close();
	}
}


