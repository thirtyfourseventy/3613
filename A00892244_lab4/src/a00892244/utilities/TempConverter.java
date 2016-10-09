/**
 * 
 */
package a00892244.utilities;

/**
 * @author Edward Lambke A00892244
 *
 */
public class TempConverter {

	/**
	 * 
	 */
	public TempConverter() {
		// TODO Auto-generated constructor stub
	}

	public static int calculateCelsius(String farhenheit) {
		double number = Double.parseDouble(farhenheit);
		return (int) (5.0 / 9.0 * ( number - 32 ));
	}
	
	public static int calculateFarhenheit(String celsius) {
		double number = Double.parseDouble(celsius);
		return (int) (9.0 / 5.0 * number + 32);

	}
	
}
