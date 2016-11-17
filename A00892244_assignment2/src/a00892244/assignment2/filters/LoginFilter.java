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

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		System.out.println("Filter this!");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		boolean authenticated = false;

		if (session.getAttribute("authenticated") != null) {
			authenticated = true;
		}

		if (session.getAttribute("authenticated") == null) {
			if (request.getParameter("password") != null) {
				System.out.println("password = " + request.getParameter("password").toString());
				if (request.getParameter("password").toString().equals(PASSWORD)) {
					session.setAttribute("authenticated", true);
					authenticated = true;
				}
			}
		}

		if (!authenticated) {
			System.out.println("Back to login!");
			RequestDispatcher dispatcher = req.getSession().getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}


	public void destroy() {
	}


	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
