<!-- Edward Lambke A00892244 -->
<!-- JSP init() -->
<%@page import="a00892244.utilities.TempConverter"%>
<%!private ServletContext servletContext;
	private String prompt = "";
	private String temperature = "";
	private String unit = "";

	public void jspInit() {
		ServletConfig config = getServletConfig();
		prompt = config.getInitParameter("instruction");
		System.out.print(prompt);
	}%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>A00892244 Lab4</title>
<link rel='stylesheet' type='text/css' href='assets/styles.css'>
</head>
</head>

<%!public boolean isTempAndUnitEntered(HttpServletRequest request) {
		String temp = request.getParameter("temp");
		String unit = request.getParameter("unit");

		if (temp != null && temp.trim().length() > 0 && unit != null && unit.trim().length() > 0)
			return true;
		else
			return false;
	}%>


<body>
	<header>
		<h1>Edward Lambke A00892244 Lab3</h1>
	</header>

	<section>
		<%
			if (!isTempAndUnitEntered(request)) {
		%>
		<form action="./">
			<h1>
				<%
					out.print(prompt);
				%>
			</h1>

			<h3>The current time is</h3>
			<h3>
				<%=new java.util.Date()%>
			</h3>

			<input type="text" name="temp"> <input type="submit"
				value="Submit"> <br /> convert temperature to <br /> <input
				type="radio" name="unit" value="celsius" checked> Celsius <br />
			<input type="radio" name="unit" value="fahrenheit">
			Fahrenheit <br />

		</form>
		<%
			}
		%>


		<%
			if (isTempAndUnitEntered(request)) {
				temperature = request.getParameter("temp");
				unit = request.getParameter("unit");
		%>
		<h2>
			<%
				out.print(temperature);
			%>
			converted to
			<%
				out.print(unit);
			%>
			is:
			<%
				try {
						if (unit.matches("celsius")) {
							out.print(TempConverter.calculateCelsius(temperature));
						} else {
							out.print(TempConverter.calculateFarhenheit(temperature));
						}
						response.setHeader("Refresh", "3; URL=./");

					} catch (NumberFormatException e) {
						response.sendError(response.SC_BAD_REQUEST,
								"Invalid user input, \"" + temperature + "\".  Enter ##.#");
					}
			%>

		</h2>
		<%
			}
		%>



	</section>
</body>
</html>