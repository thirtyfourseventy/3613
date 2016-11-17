package a00892244.assignment2.filters;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Edward Lambke A00892244
 */
public class LoginFilter implements Filter {
	private String PASSWORD = "java3613";

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {

	
		System.out.println("Filter this!");
	
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		String redirect = "login";
		
		if (session.getAttribute("authenticated") != null) {
			redirect = "proceed";
		}
		
		if (session.getAttribute("authenticated") == null) {		
			if (request.getParameter("password") != null) {
				System.out.println("password = " + request.getParameter("password").toString());
				if (request.getParameter("password").toString().equals(PASSWORD)){
					session.setAttribute("authenticated", true);
					redirect = "table";
				}				
			}
			
		}
			
		if (redirect.equals("table")) {
			System.out.println("proceed to table!");
			RequestDispatcher dispatcher = req.getSession().getServletContext().getRequestDispatcher("/assignment2");
			dispatcher.forward(request, response);
		}

		else if (redirect.equals("login")){
			System.out.println("Back to login!");
			RequestDispatcher dispatcher = req.getSession().getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
		else  {
			chain.doFilter(request, response);
		}
	}

	/**
	 * Create a table of banned sites based on initialization parameters .
	 */
	public void init(FilterConfig config) throws ServletException {
		
//		bannedSiteTable = new HashSet<String>();
//		String bannedSites = config.getInitParameter("bannedSites");
//		if (bannedSites == null) {
//			return;
//		}
//		// Split using one or more white spaces
//		String[] sites = bannedSites.split("\\s++");
//		for (String bannedSite : sites) {
//			bannedSiteTable.add(bannedSite);
//			System.out.println("Banned " + bannedSite);
//		}
	}

	public void destroy() {
	}

	private String getReferringHost(String refererringURLString) {
		
		try {
			URL referringURL = new URL(refererringURLString);
			return (referringURL.getHost());
		} catch (MalformedURLException mue) { // Malformed or null
			return (null);
		}
	}

	// Replacement response that is returned to users
	// who are from or referred here by a banned site.

	private void showWarning(ServletResponse response, String bannedSite)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 "
				+ "Transitional//EN\">\n";
		out.println(docType + "<HTML>\n"
				+ "<HEAD><TITLE>Access Prohibited</TITLE></HEAD>\n"
				+ "<BODY BGCOLOR=\"WHITE\">\n" + "<H1>Access Prohibited</H1>\n"
				+ "Sorry, access from or via " + bannedSite + "\n"
				+ "is not allowed.\n" + "</BODY></HTML>");
	}
}
