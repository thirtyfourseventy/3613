/**
 * Edward Lambke a00892244
 */

package a00892244;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilities.RegexValidator;

/**
 * Servlet implementation class A00892244_lab2
 */
@WebServlet("/A00892244_lab2")
public class A00892244_lab2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String emailRegex = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
	private static final String phoneRegex = "^\\(?(\\d{3})\\)?[\\.\\-\\/ ]?(\\d{3})[\\.\\-\\/ ]?(\\d{4})$";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public A00892244_lab2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Reading All Request Parameters";

		out.println("<BODY BGCOLOR=\"#FDF5E6\">\n" + "<H1 ALIGN=CENTER>" + title + "</H1>\n"
				+ "<TABLE BORDER=1 ALIGN=CENTER>\n" + "<TR BGCOLOR=\"#FFAD00\">\n"
				+ "<TH>Parameter Name<TH>Parameter Value(s)");
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			out.print("<TR><TD>" + paramName + "</TD>\n<TD>");
			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() == 0)
					out.println("<I>No Value</I></TD>");
				else if (paramName.equals("email")) {
					if (RegexValidator.isValidInput(paramValue, emailRegex)) {
						out.println(paramValue + "</TD>");
					} else {
						out.println("Bad email format");
					}
				} else if (paramName.equals("phone")) {
					if (RegexValidator.isValidInput(paramValue, phoneRegex)) {
						out.println(paramValue + "</TD>");
					} else {
						out.println("idiot!");
					}
				} else {
					out.println(paramValue + "</TD>");
				}
			} else {
				out.println("<UL>");
				for (int i = 0; i < paramValues.length; i++) {
					out.println("<LI>" + paramValues[i]);
				}
				out.println("</UL>");
				
			}
			out.println("</TR>");
		}
		out.println("<TR><TD><a href='index.html'>Try again</a></TD><TD></TD></TR>");
		out.println("</TABLE>\n</BODY></HTML>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
