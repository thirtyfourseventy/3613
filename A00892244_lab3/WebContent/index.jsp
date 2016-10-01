<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>A00892244 Lab3</title>
<link rel='stylesheet' type='text/css' href='assets/styles.css'></head>
</head>



<body>
	<header>
	<h1>Edward Lambke A00892244 Lab4</h1>
	</header>

	<section>


	<form action="./"
		style="display: <%if (request.getParameter("name") != null && request.getParameter("name") != "") {
				out.print("none");
			} else {
				out.print("block");
			}%>">
		<h1>Type your first name and press "Submit" </h1>
		<input type="text" name="name"> 
		<input type="submit" value="Submit">
		
	</form>

	<div
		style="display: <%if (request.getParameter("name") != null && request.getParameter("name") != "") {
				out.print("block");
			} else {
				out.print("none");
			}%>">
		<h1>
			Hello,
			<%= request.getParameter("name") %></h1>
		<h2>Welcome to JavaServer Pages!</h2>

	</div>

	</section>
</body>
</html>