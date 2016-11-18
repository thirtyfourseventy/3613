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

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		boolean redirectToServlet = false;

		if (session.getAttribute("authenticated") != null) {
			redirectToServlet = true;
		}

		if (session.getAttribute("authenticated") == null) {
			if (request.getParameter("password") != null) {
				System.out.println("password = " + request.getParameter("password").toString());
				redirectToServlet = true;
			}
		}

		if (!redirectToServlet) {
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
