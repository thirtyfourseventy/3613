package a00892244.assignment1;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import a00892244.assignment1.data.BrewingRecord;
import a00892244.assignment1.data.DataManager;

import java.io.*;
import java.util.List;

@SuppressWarnings("serial")
// @WebServlet("/assignment1")
public class Assignment1Servlet extends HttpServlet {

	private DataManager dataManager;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		dataManager = new DataManager(getServletContext().getInitParameter("Driver"),
				getServletContext().getInitParameter("URL"), getServletContext().getInitParameter("User"),
				getServletContext().getInitParameter("Password"), getServletContext().getInitParameter("DatabaseName"),
				getServletContext().getInitParameter("Table"));

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<BrewingRecord> brewingRecords;

			brewingRecords = dataManager.getAll();

			request.setAttribute("records", brewingRecords);

			response.setContentType("text/html");

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/table.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		try {
			double og = 0;
			double fg = 0;

			if (!request.getParameter("og").trim().equals("") && request.getParameter("og") != null) {
				og = Double.parseDouble(request.getParameter("og").trim());
			}
			if (!request.getParameter("fg").trim().equals("") && request.getParameter("fg") != null) {
				fg = Double.parseDouble(request.getParameter("fg").trim());
			}
			if (request.getParameter("name").trim().length() == 0) {
				throw new Exception("name is a required field");
			}
			if (request.getParameter("number").trim().length() == 0) {
				throw new Exception("number is a required field");
			}

			BrewingRecord record = new BrewingRecord(Integer.parseInt(request.getParameter("number").trim()),
					request.getParameter("name").trim(), request.getParameter("brew_date").trim(),
					request.getParameter("grist").trim(), request.getParameter("hops").trim(),
					request.getParameter("water").trim(), request.getParameter("yeast").trim(),
					request.getParameter("yeast_code").trim(), request.getParameter("pitching_temp").trim(),
					request.getParameter("ferment_temp").trim(), og, fg, request.getParameter("package_date").trim(), request.getParameter("notes").trim());

			if (action.contentEquals("Create")) {

				record.newUidpk();
				dataManager.addRecord(record);

				response.setContentType("text/html");

			}

			if (action.contains("Delete")) {

				record.setUidpk(request.getParameter("uidpk"));
				dataManager.deleteRecord(record);

			}

			if (action.contains("Update")) {
				record.setUidpk(request.getParameter("uidpk"));
				dataManager.updateRecord(record);
			}

		} catch (NumberFormatException nfe) {
			request.setAttribute("error", HttpServletResponse.SC_BAD_REQUEST + " Invalid user input, Enter ##.####");

		} catch (Exception e) {
			request.setAttribute("error",
					HttpServletResponse.SC_BAD_REQUEST + " Invalid user input, " + e.getMessage());
		}
		doGet(request, response);

	}
}
