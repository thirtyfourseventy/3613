package a00892244.assignment1;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import a00892244.assignment1.data.BrewingRecord;
import a00892244.assignment1.data.DataManager;

import java.io.*;
import java.util.List;

@SuppressWarnings("serial")
//@WebServlet("/assignment1")
public class Assignment1Servlet extends HttpServlet {

	private DataManager dataManager;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		dataManager = new DataManager(getServletContext().getInitParameter("Driver"), getServletContext().getInitParameter("URL"), getServletContext().getInitParameter("User"),
				getServletContext().getInitParameter("Password"), getServletContext().getInitParameter("DatabaseName"), getServletContext().getInitParameter("Table"));

	}
	
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				
		List<BrewingRecord> brewingRecords = dataManager.getAll(); 
		
		request.setAttribute("test", brewingRecords.get(1).getName());
		
		request.setAttribute("records", brewingRecords);
		
		response.setContentType("text/html");

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/table.jsp");
		dispatcher.forward(request, response);

	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		

		try {
			
			System.out.println("Here we are!");
			
			BrewingRecord newRecord = new BrewingRecord(
					Integer.parseInt(request.getParameter("number")),
					request.getParameter("name"),
					request.getParameter("brew_date"),
					request.getParameter("grist"),
					request.getParameter("hops"),
					request.getParameter("water"),
					request.getParameter("yeast"),
					request.getParameter("yeast_code"),
					request.getParameter("pitching_temp"),
					request.getParameter("ferment_temp"),
					Double.parseDouble(request.getParameter("og")),
					Double.parseDouble(request.getParameter("fg")),
					Double.parseDouble(request.getParameter("abv")),
					request.getParameter("package_date"),
					request.getParameter("notes")
				);
			
			dataManager.addRecord(newRecord);
			

			response.setContentType("text/html");

			doGet(request, response);
		} catch (NumberFormatException nfe) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"Invalid user input, \"" + request.getParameter("input")
							+ "\". Enter ##.#");
		}

	}
}
