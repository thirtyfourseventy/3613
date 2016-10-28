/**
 * 
 */
package a00892244.lab7;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.ResultSet;

import javax.servlet.*;
import javax.servlet.http.*;

import a00892244.lab7.utils.DataManager;

/**
 * @author Edward Lambke A00892244
 *
 */
public class Lab07Servlet extends HttpServlet {

	private DataManager dataManager;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		System.out.println("Starting...");

		dataManager = new DataManager(getServletContext().getInitParameter("Driver"),
				getServletContext().getInitParameter("URL"), getServletContext().getInitParameter("User"),
				getServletContext().getInitParameter("Password"), getServletContext().getInitParameter("DatabaseName"));

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String query = request.getParameter("query");
			ResultSet results = dataManager.executeQuery(query);

			request.setAttribute("results", results);

			response.setContentType("text/html");

			if (session.getAttribute("query") == null) {
				session.setAttribute("query", query);
			} else if (!session.getAttribute("query").equals(query.trim())) {
				session.setAttribute("query", query);
			}

			Cookie cookie = new Cookie("query", URLEncoder.encode(query, "UTF-8"));
			cookie.setMaxAge(3600);
			response.addCookie(cookie);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/results.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error",
					HttpServletResponse.SC_BAD_REQUEST + " Invalid user input, " + e.getMessage());
			response.setContentType("text/html");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}

	}

}
