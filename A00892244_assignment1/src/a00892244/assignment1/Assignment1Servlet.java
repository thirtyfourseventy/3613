package a00892244.assignment1;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import a00892244.assignment1.data.BrewingRecord;
import a00892244.assignment1.data.DataManager;

import java.io.*;
import java.util.List;

@SuppressWarnings("serial")
@WebServlet("/assignment1")
public class Assignment1Servlet extends HttpServlet {

	private DataManager dataManager;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		dataManager = new DataManager(getServletContext().getInitParameter("Driver"), getServletContext().getInitParameter("URL"), getServletContext().getInitParameter("User"),
				getServletContext().getInitParameter("Password"), getServletContext().getInitParameter("DatabaseName"), getServletContext().getInitParameter("Table"));
		
		System.out.println(dataManager.getAll().toString());
	}
	
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				
		List<BrewingRecord> brewingRecords = dataManager.getAll(); 
		
		System.out.println(brewingRecords.get(1).getName());
		
		request.setAttribute("test", brewingRecords.get(1).getName());
		
		request.setAttribute("records", brewingRecords);
		
		response.setContentType("text/html");

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/table.jsp");
		dispatcher.forward(request, response);

	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		double inputTemperature = 0.0;

		double returnValue = 0.0;

		request.setAttribute("inputValue", request.getParameter("input"));

		request.setAttribute("choice", request.getParameter("tempType"));

		try {

			inputTemperature = Double
					.parseDouble(request.getParameter("input"));
			response.setHeader("Refresh", "5; URL=" + "index.jsp");

			if (request.getParameter("tempType").equals("celsius")) {
				
//				returnValue = convert.convertToCelsius(inputTemperature);
			} else {
				
//				returnValue = convert.convertToFahrenheit(inputTemperature);
			}

			request.setAttribute("output", returnValue);

			response.setContentType("text/html");

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
			dispatcher.forward(request, response);
		} catch (NumberFormatException nfe) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"Invalid user input, \"" + request.getParameter("input")
							+ "\". Enter ##.#");
		}

	}
}
