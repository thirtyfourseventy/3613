<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>A00892244 Edward Lambke</title>
<link rel='stylesheet' type='text/css' href='assets/styles.css'>
</head>
<body>

<%
if (request.getHeader("Authorization") == null) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
}
%>

<header>
<h1>Edward Lambke A00892244</h1>
</header>

<section>
<h1>Assignment 1</h1>

<h2>Homebrewing Records Database </h2>

<FORM METHOD="POST" ACTION="assignment1">
<input type="hidden" name="access_type" value="read_write">
<input type="submit" name="action" value="Login for read write access">
</FORM>

<FORM METHOD="POST" ACTION="assignment1">
<input type="submit" name="action" value="Enter with read only access">
</FORM>

</section>

</body>
</html>