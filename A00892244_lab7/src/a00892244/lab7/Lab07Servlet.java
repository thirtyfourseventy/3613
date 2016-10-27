/**
 * 
 */
package a00892244.lab7;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import a00892244.lab7.data.DataManager;

/**
 * @author elambke
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
			ResultSet results = dataManager.executeQuery(request.getParameter("query"));
			
			request.setAttribute("results", results);
			
			response.setContentType("text/html");
			session.setAttribute("query", request.getParameter("query"));
			
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	

	
}
