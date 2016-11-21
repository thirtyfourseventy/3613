package a00892244.assignment2;

import javax.servlet.*;
import javax.servlet.http.*;

import a00892244.assignment2.data.BrewingRecord;
import a00892244.assignment2.data.DataManager;
import a00892244.assignment2.data.InputFilter;
import a00892244.assignment2.decode.Decoder;
import a00892244.tribble.util.Base64Decoder;

import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 
 * @author Edward Lambke A00892244
 *
 */

@SuppressWarnings("serial")
public class Assignment2Servlet extends HttpServlet {

	private DataManager dataManager;
	private Properties props;
	private String dbPropertiesPath;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		dbPropertiesPath = getServletContext().getInitParameter("dbPropertiesPath");
		props = new Properties();

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
			e.printStackTrace();
			request.setAttribute("error", HttpServletResponse.SC_BAD_REQUEST
					+ " Invalid user input. An error has occurred: " + e.getMessage());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}

	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		List<String> queries;

		if (session.getAttribute("queries") != null) {
			queries = (ArrayList<String>) session.getAttribute("queries");
		} else {
			queries = new ArrayList<String>();
		}

		if (action.contains("logout")) {
			session.setAttribute("authenticated", null);
		}

		else if (action.contains("login")) {
			try {

				Decoder decoder = new Decoder();

				String properties = decoder.readFromFileAndDecrypt(request.getParameter("password").toString(),
						getServletContext().getResourceAsStream(dbPropertiesPath));

				// System.out.println(properties);

				props.load(new ByteArrayInputStream(properties.getBytes()));

				// System.out.println("servlet password = " +
				// request.getParameter("password").toString());

				if (dataManager == null) {
					System.out.println("creating DataManager");
					dataManager = new DataManager(props.getProperty("Driver"), props.getProperty("URL"),
							props.getProperty("User"), props.getProperty("Password"), props.getProperty("DatabaseName"),
							props.getProperty("Table"));
				}

				session.setAttribute("authenticated", true);
			} catch (Exception e) {
				session.setAttribute("authenticated", null);
				request.setAttribute("error", "Invalid_Password");
				System.out.println("***********ERROR ON LOGIN***************");
				e.printStackTrace();
				System.out.println("***********ERROR ON LOGIN***************");
			}
		}

		else {
			try {
				String number = InputFilter.filter(request.getParameter("number").trim());
				String name = InputFilter.filter(request.getParameter("name").trim());
				String brew_date = InputFilter.filter(request.getParameter("brew_date").trim());
				String water = InputFilter.filter(request.getParameter("water").trim());
				String yeast = InputFilter.filter(request.getParameter("yeast").trim());
				String yeast_code = InputFilter.filter(request.getParameter("yeast_code").trim());
				String pitching_temp = InputFilter.filter(request.getParameter("pitching_temp").trim());
				String ferment_temp = InputFilter.filter(request.getParameter("ferment_temp").trim());
				String og = InputFilter.filter(request.getParameter("og").trim());
				String fg = InputFilter.filter(request.getParameter("fg").trim());
				String package_date = InputFilter.filter(request.getParameter("package_date").trim());
				String grist = InputFilter.filter(request.getParameter("grist").trim());
				String hops = InputFilter.filter(request.getParameter("hops").trim());
				String notes = InputFilter.filter(request.getParameter("notes").trim());

				BrewingRecord record = new BrewingRecord(number, name, brew_date, grist, hops, water, yeast, yeast_code,
						pitching_temp, ferment_temp, og, fg, package_date, notes);

				if (action.contentEquals("Create")) {
					record.newUidpk();
					queries.add(dataManager.addRecord(record));

				}

				if (action.contains("Delete")) {
					record.setUidpk(InputFilter.filter(request.getParameter("uidpk")));
					queries.add(dataManager.deleteRecord(record));

				}

				if (action.contains("Update")) {
					record.setUidpk(request.getParameter("uidpk"));
					queries.add(dataManager.updateRecord(record));
				}

				session.setAttribute("queries", queries);

			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("error",
						HttpServletResponse.SC_BAD_REQUEST + " Invalid user input, " + e.getMessage());
			}
		}

		// End Table Page Actions

		response.setContentType("text/html");

		if (session.getAttribute("authenticated") != null) {
			doGet(request, response);
		} else {
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
