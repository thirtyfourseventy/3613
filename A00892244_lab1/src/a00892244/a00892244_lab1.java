/**
 * Edward Lambke A00892244
 */

package a00892244;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class a00892244_lab1
 */
@WebServlet("/a00892244_lab1")
public class a00892244_lab1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public a00892244_lab1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println(
				"<!DOCTYPE html><html><head><title>A00892244 COMP-3613 Lab1</title>"
				+ "<link rel='stylesheet' type='text/css' href='assets/lab1styles.css'></head>"
				+ "<body><header><h1>comp-3613 lab 1</h1></header>"
				+ "<section><h1>Edward Lambke</h1><h2>a00892244</h2><img src = 'assets/hbk.png' /></section></body></html>");
		out.close();
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
