/**
 * 
 */
package a00892244.assignment1.data;

/**
 * @author Edward Lambke A00892244
 * Stolen from ServletUtilities.java from class 2 examples
 */
public class InputFilter {

	/**
	 * 
	 */
	public InputFilter() {
		// TODO Auto-generated constructor stub
	}

	
	  public static String filter(String input) {
		    StringBuffer filtered = new StringBuffer(input.length());
		    char c;
		    for(int i=0; i<input.length(); i++) {
		      c = input.charAt(i);
		      if (c == '<') {
		        filtered.append("&lt;");
		      } else if (c == '>') {
		        filtered.append("&gt;");
		      } else if (c == '"') {
		        filtered.append("&quot;");
		      } else if (c == '&') {
		        filtered.append("&amp;");
		      } else {
		        filtered.append(c);
		      }
		    }
		    return(filtered.toString());
		  }
}
