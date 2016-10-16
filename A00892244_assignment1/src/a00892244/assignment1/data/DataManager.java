/**
 * 
 */
package a00892244.assignment1.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Edward Lambke A00892244
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
			Properties props = new Properties();
			props.load(new FileInputStream("files" + File.separator + "a00892244dbprops.properties"));

			Class.forName(props.getProperty("Driver"));
			con = DriverManager.getConnection(props.getProperty("URL"), props);

			tableName = props.getProperty("Table");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public DataManager(String driver, String url, String user, String password, String dbname, String table) {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);

			tableName = table;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Run as Java Application to invoke createTable
	 */
	public void createTable() {
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			stmt.execute("IF OBJECT_ID('" + tableName + "') IS NOT NULL DROP TABLE " + tableName);

			String createTable = "CREATE TABLE " + tableName + "(uidpk CHAR(64), number INT, name CHAR(64), "
					+ "brew_date CHAR(64), grist CHAR(512), hops CHAR(512), water CHAR(64), yeast CHAR(64), "
					+ "yeast_code CHAR(64), pitching_temp CHAR(64), ferment_temp CHAR(64), og DECIMAL(16,3), "
					+ "fg DECIMAL(16,3), abv DECIMAL(16,3), package_date CHAR(64), notes CHAR(512)) ";

			stmt.execute(createTable);

			String createIndex = "CREATE UNIQUE INDEX tableKey " + "ON " + tableName + " (uidpk)";
			stmt.execute(createIndex);

			String strInsert = "INSERT INTO " + tableName + "(uidpk, number, name, brew_date, grist, hops, water, "
					+ "yeast, yeast_code, pitching_temp, ferment_temp, og) "
					+ "VALUES('edb41118-a77a-4529-89d6-1175a0578f0f', 117,'Old Course Ale','10/08/2016','8lb 10oz Simpsons GP, 4oz Simpsons C-120, 2oz TF Black', "
					+ "'1oz 2016 Hallertau@FW','1tsp CaCl, .5tsp CaSO4','S-04','16908 239 0837 08 2017','12C','16C',1.042)";

			@SuppressWarnings("unused")
			int rowsAffected = stmt.executeUpdate(strInsert);

			strInsert = "INSERT INTO " + tableName + "(uidpk, number, name, brew_date, grist, hops, water, "
					+ "yeast, yeast_code, pitching_temp, ferment_temp, og, fg, abv, package_date, notes) "
					+ "VALUES('bd851aa0-2c5c-40a0-9c04-d6bc63a9d3a1',116,'HarvestGoblin','09-08-2016','9lb TF GP,13oz Bairds C75,3oz Carafa Sp 2,1.5oz Black Strap Mollases', "
					+ "'1oz 4%AA EKG @ FW, 2.75oz Wet 2016 Hallertau + Goldings, 2oz dry 2016 Saaz @ 0, 1oz dry 2016 Cascade @ 0', "
					+ "'1tsp CaCl, 1tsp CaSO4', 'S-04','repitch from 115','12C','18C',1.044,1.012,4.289,'09-19-2016', '14g tubinado,1gal hobgoblin cask')";
			rowsAffected = stmt.executeUpdate(strInsert);

			strInsert = "INSERT INTO " + tableName + "(uidpk, number, name, brew_date, grist, hops, water, "
					+ "yeast, yeast_code, pitching_temp, ferment_temp, og, fg, abv, package_date, notes) "
					+ "VALUES('f7437de1-6a42-4857-a748-83e9ba8d3fa0',115,'BBQ Bitter','08-21-2016','5lb TF GP, 2lb Boh Pils, 8oz Bairds C-75, 4oz TF Brown,5oz Homemade invert 3', "
					+ "'1oz 2015 Hallertau @ FW, 1oz @ 0','1tsp CaCl, 1tsp CaSO4','S-04','16908 239 0837 08 2017','14C','18C',1.036,1.012, "
					+ "3.217,'8-27-2016','1gal hobgoblin cask, 4.5gal in corny')";
			rowsAffected = stmt.executeUpdate(strInsert);

			strInsert = "INSERT INTO " + tableName + "(uidpk, number, name, brew_date, grist, hops, water, "
					+ "yeast, yeast_code, ferment_temp, og, fg, abv, package_date) "
					+ "VALUES('e020e69b-ad81-415a-96d9-d594de2a8acc',114,'Homegrown IPA No Chill','07-30-2016','13lb Canadian 2-row, .5lb dextrose',"
					+ "'2013 Willamette 4oz @ FW, 2012 Willamette 3oz @ 45min, 2012 Cascade/Willamette blend 2oz @ 30min,"
					+ "2013 Willamette 2oz @ 15min, 2013 Willamette 3oz in Chill Keg','1tsp CaSO4','US-05','repitch from #113',"
					+ "'18C',1.060,1.009,6.836,'08-20-2016')";
			rowsAffected = stmt.executeUpdate(strInsert);

			strInsert = "INSERT INTO " + tableName + "(uidpk, number, name, brew_date, grist, hops, water, "
					+ "yeast, yeast_code, pitching_temp, ferment_temp, og, fg, abv, package_date, notes) "
					+ "VALUES('5ec92198-03b4-41bb-b5a9-91db230760cd',113,'Black Beer thing','07-15-2016','10lbs Weyermann Bohemian, 6oz Carafa sp 2',"
					+ "'2015 Homegrown Hallertau: 1oz @ FW, 1oz in chill keg, Store bought Saaz 1oz 4.5%AA @ FW','',"
					+ "'US-05','11224 022 1717 01 2017','','18C','1.048',1.012,4.825737265,'07-30-2016','')";
			rowsAffected = stmt.executeUpdate(strInsert);

			queryResults = stmt.executeQuery("SELECT * FROM " + tableName);

			display();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void addRecord(BrewingRecord record) throws Exception {

		System.out.println("adding " + record.dataToString());
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String strInsert = "INSERT INTO " + tableName + "(" + record.fieldNamesToString() + ") " + "VALUES("
					+ record.dataWithQuotesToString() + ")";

			System.out.println(strInsert);

			int rowsAffected = stmt.executeUpdate(strInsert);
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
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

	public void display() {
		try {
			while (queryResults.next()) {
				System.out.print(queryResults.getInt("number"));
				System.out.print(". " + queryResults.getString("name").trim());
				System.out.println(" " + queryResults.getString("og").trim());
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		System.out.println("\n");
	}

	public List<BrewingRecord> getAll() throws Exception {
		ArrayList<BrewingRecord> allRecords = new ArrayList<BrewingRecord>();
		String select = "SELECT * FROM " + tableName + " ORDER BY number DESC";

		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			queryResults = stmt.executeQuery(select);
			while (queryResults.next()) {
				BrewingRecord record = new BrewingRecord(queryResults.getString("uidpk"), queryResults.getInt("number"),
						queryResults.getString("name"), queryResults.getString("brew_date"),
						queryResults.getString("grist"), queryResults.getString("hops"),
						queryResults.getString("water"), queryResults.getString("yeast"),
						queryResults.getString("yeast_code"), queryResults.getString("pitching_temp"),
						queryResults.getString("ferment_temp"), queryResults.getDouble("og"),
						queryResults.getDouble("fg"), queryResults.getDouble("abv"),
						queryResults.getString("package_date"), queryResults.getString("notes"));
				allRecords.add(record);
			}
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		return allRecords;
	}

	public void deleteRecord(BrewingRecord record) throws Exception {
		String delete = "DELETE FROM " + tableName + " WHERE uidpk = '" + record.getUidpk() + "'";

		System.out.println(delete);

		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(delete);

		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}

	public void updateRecord(BrewingRecord record) throws Exception {

		try {
			String update = "UPDATE " + tableName + " SET " + " number = '" + record.getNumber() + "'," + " name = '"
					+ record.getName() + "'," + " brew_date = '" + record.getBrew_date() + "'," + " grist = '"
					+ record.getGrist() + "'," + " hops = '" + record.getHops() + "'," + " water = '"
					+ record.getWater() + "'," + " yeast = '" + record.getYeast() + "'," + " yeast_code = '"
					+ record.getYeast_code() + "'," + " pitching_temp = '" + record.getPitching_temp() + "',"
					+ " ferment_temp = '" + record.getFerment_temp() + "'," + " og = " + record.getOg() + "," + " fg = "
					+ record.getFg() + "," + " abv = " + record.getAbv() + "," + " package_date = '"
					+ record.getPackage_date() + "'," + " notes = '" + record.getNotes() + "'" + " WHERE uidpk = '"
					+ record.getUidpk() + "'";

			System.out.println(update);

			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(update);

		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}

	public static void main(String[] args) {

		System.out.println("Starting..");
		DataManager db = new DataManager();

		db.createTable();

		db.close();
	}
}
