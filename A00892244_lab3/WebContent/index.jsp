<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	ServletConfig servletConfig = getServletConfig();
	ServletContext servletContext = servletConfig.getServletContext();
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>A00892244 Lab3</title>
<link rel='stylesheet' type='text/css' href='assets/styles.css'>
</head>
</head>

<%!public boolean isNameEntered(HttpServletRequest request) {
		if (request.getParameter("name") != null && request.getParameter("name") != "")
			return true;
		else
			return false;
	}%>


<body>
	<header>
	<h1>Edward Lambke A00892244 Lab3</h1>
	</header>

	<section>
	<form action="./"
		style="display: <%if(isNameEntered(request)) {
				out.print("none");
			} else {
				out.print("block");
			}%>">
		<h1>
			<%
				out.print(servletContext.getInitParameter("question"));
			%>
		</h1>
		<input type="text" name="name"> <input type="submit"
			value="Submit">

	</form>

	<div
		style="display: <%if(isNameEntered(request)) {
				out.print("block");
			} else {
				out.print("none");
			}%>">
		<h1>
			Hello,
			<%=request.getParameter("name")%></h1>
		<h2>
			<%
				out.print(servletContext.getInitParameter("response"));
			%>
		</h2>

	</div>

	</section>
</body>
</html>