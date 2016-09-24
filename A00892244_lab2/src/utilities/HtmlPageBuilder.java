/**
 * 
 */
package utilities;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Edward Lambke A00892244
 *
 */
public class HtmlPageBuilder {

	private static final String EMAIL_REGEX = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
	private static final String PHONE_REGEX = "^\\(?(\\d{3})\\)?[\\.\\-\\/ ]?(\\d{3})[\\.\\-\\/ ]?(\\d{4})$";

	/**
	 * 
	 */
	public HtmlPageBuilder() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public static String returnValidationHtml(HttpServletRequest request){
		String title = "A00892244 COMP-3613 Lab2";
		
		StringBuilder html = new StringBuilder();

		html.append("<!DOCTYPE html><html><head><title>" + title + "</title>");
		html.append("<link rel='stylesheet' type='text/css' href='assets/styles.css'></head>");
		html.append("<BODY><HEADER>\n" + "<H1>" + title + "</H1><H2>Form Validation Results</H2></HEADER>\n"
				+ "<SECTION><TABLE>\n" + "<TR>\n"
				+ "<TH>Parameter Name<TH>Parameter Value(s)");
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			html.append("<TR><TD>" + paramName + "</TD>\n<TD>");
			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() == 0)
					html.append("<em>Please enter your " + paramName + "</em></TD>");
				else if (paramName.equals("email")) {
					if (RegexValidator.isValidInput(paramValue, EMAIL_REGEX)) {
						html.append(paramValue + "</TD>");
					} else {
						html.append("<em>Your email address has been incorrectly formatted (xxx@xxx.xxx)</em>");
					}
				} else if (paramName.equals("phone")) {
					if (RegexValidator.isValidInput(paramValue, PHONE_REGEX)) {
						html.append(paramValue + "</TD>");
					} else {
						html.append("<em>Your phone number has been incorrectly formatted (000-000-0000)</em>");
					}
				} else {
					html.append(paramValue + "</TD>");
				}
			} else {
				html.append("<UL>");
				for (int i = 0; i < paramValues.length; i++) {
					html.append("<LI>" + paramValues[i]);
				}
				html.append("</UL>");
				
			}
			html.append("</TR>");
		}
		html.append("<TR><TD></TD><TD><a href='index.html'>Try again</a></TD></TR>");
		html.append("</TABLE></SECTION>\n</BODY></HTML>");
		return html.toString();
	}

}
