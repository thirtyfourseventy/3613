/**
 * 
 */
package a00892244.assignment1.data;

/**
 * @author elambke
 *
 */
public class BrewingRecord {

	int number;
	String name;
	String brew_date;
	String grist;
	String hops;
	String water;
	String yeast;
	String yeast_code;
	String pitching_temp;
	String ferment_temp;
	double og;
	double fg;
	double abv;
	String package_date;
	String notes;
	
	
	/**
	 * 
	 */
	public BrewingRecord() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param number
	 * @param name
	 * @param brew_date
	 * @param grist
	 * @param hops
	 * @param water
	 * @param yeast
	 * @param yeast_code
	 * @param pitching_temp
	 * @param ferment_temp
	 * @param og
	 * @param fg
	 * @param abv
	 * @param package_date
	 * @param notes
	 */
	public BrewingRecord(int number, String name, String brew_date, String grist, String hops, String water,
			String yeast, String yeast_code, String pitching_temp, String ferment_temp, double og, double fg,
			double abv, String package_date, String notes) {
		super();
		this.number = number;
		this.name = name;
		this.brew_date = brew_date;
		this.grist = grist;
		this.hops = hops;
		this.water = water;
		this.yeast = yeast;
		this.yeast_code = yeast_code;
		this.pitching_temp = pitching_temp;
		this.ferment_temp = ferment_temp;
		this.og = og;
		this.fg = fg;
		this.abv = abv;
		this.package_date = package_date;
		this.notes = notes;
	}


	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}


	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the brew_date
	 */
	public String getBrew_date() {
		return brew_date;
	}


	/**
	 * @param brew_date the brew_date to set
	 */
	public void setBrew_date(String brew_date) {
		this.brew_date = brew_date;
	}


	/**
	 * @return the grist
	 */
	public String getGrist() {
		return grist;
	}


	/**
	 * @param grist the grist to set
	 */
	public void setGrist(String grist) {
		this.grist = grist;
	}


	/**
	 * @return the hops
	 */
	public String getHops() {
		return hops;
	}


	/**
	 * @param hops the hops to set
	 */
	public void setHops(String hops) {
		this.hops = hops;
	}


	/**
	 * @return the water
	 */
	public String getWater() {
		return water;
	}


	/**
	 * @param water the water to set
	 */
	public void setWater(String water) {
		this.water = water;
	}


	/**
	 * @return the yeast
	 */
	public String getYeast() {
		return yeast;
	}


	/**
	 * @param yeast the yeast to set
	 */
	public void setYeast(String yeast) {
		this.yeast = yeast;
	}


	/**
	 * @return the yeast_code
	 */
	public String getYeast_code() {
		return yeast_code;
	}


	/**
	 * @param yeast_code the yeast_code to set
	 */
	public void setYeast_code(String yeast_code) {
		this.yeast_code = yeast_code;
	}


	/**
	 * @return the pitching_temp
	 */
	public String getPitching_temp() {
		return pitching_temp;
	}


	/**
	 * @param pitching_temp the pitching_temp to set
	 */
	public void setPitching_temp(String pitching_temp) {
		this.pitching_temp = pitching_temp;
	}


	/**
	 * @return the ferment_temp
	 */
	public String getFerment_temp() {
		return ferment_temp;
	}


	/**
	 * @param ferment_temp the ferment_temp to set
	 */
	public void setFerment_temp(String ferment_temp) {
		this.ferment_temp = ferment_temp;
	}


	/**
	 * @return the og
	 */
	public double getOg() {
		return og;
	}


	/**
	 * @param og the og to set
	 */
	public void setOg(double og) {
		this.og = og;
	}


	/**
	 * @return the fg
	 */
	public double getFg() {
		return fg;
	}


	/**
	 * @param fg the fg to set
	 */
	public void setFg(double fg) {
		this.fg = fg;
	}


	/**
	 * @return the abv
	 */
	public double getAbv() {
		return abv;
	}


	/**
	 * @param abv the abv to set
	 */
	public void setAbv(double abv) {
		this.abv = abv;
	}


	/**
	 * @return the package_date
	 */
	public String getPackage_date() {
		return package_date;
	}


	/**
	 * @param package_date the package_date to set
	 */
	public void setPackage_date(String package_date) {
		this.package_date = package_date;
	}


	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}


	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BrewingRecord [number=" + number + ", name=" + name + ", brew_date=" + brew_date + ", grist=" + grist
				+ ", hops=" + hops + ", water=" + water + ", yeast=" + yeast + ", yeast_code=" + yeast_code
				+ ", pitching_temp=" + pitching_temp + ", ferment_temp=" + ferment_temp + ", og=" + og + ", fg=" + fg
				+ ", abv=" + abv + ", package_date=" + package_date + ", notes=" + notes + "]";
	}
	
	public String dataOnlyToString() { 
		return number + "," + name + "," + brew_date + "," + grist
				+ "," + hops + "," + water + "," + yeast + "," + yeast_code
				+ "," + pitching_temp + "," + ferment_temp + "," + og + "," + fg
				+ "," + abv + "," + package_date + "," + notes;
	}
	
	public String fieldNamesToString() {
		return "number, name, brew_date, grist, hops, water, yeast, yeast_code, pitching_temp, "
				+ "ferment_temp, og, fg, abv, package_date, notes";
	}
	
	
}
