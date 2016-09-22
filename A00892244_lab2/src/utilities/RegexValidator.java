/**
 * 
 */
package utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Edward Lambke A00892244
 *
 */
public class RegexValidator {

	public RegexValidator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static boolean isValidInput(String input, String pattern) {
		Pattern patt = Pattern.compile(pattern);
		Matcher match = patt.matcher(input);
		return match.matches();
	}

	
}
