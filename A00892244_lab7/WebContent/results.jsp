<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edward Lambke A00892244 Lab7</title>
<link rel='stylesheet' type='text/css' href='assets/styles.css'>
</head>
<body>

<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>


<header><h1><%=session.getAttribute("query") %></h1></header>

<% if(request.getAttribute("results") != null) { 
	try {
		ResultSet results = (ResultSet)request.getAttribute("results");
		ResultSetMetaData metadata = results.getMetaData();

%>
<section>
<table>


<tr><th></th><th>Data Type</th><th>Column Width</th><th>Searchable?</th><th>Nullable?</th></tr>

<%
for (int i=1; i <= metadata.getColumnCount(); i++) {
	out.print("<tr><td>" + metadata.getColumnName(i) + "</td>");
	out.print("<td>" + metadata.getColumnTypeName(i) + "</td>");
	out.print("<td>" + metadata.getColumnDisplaySize(i) + "</td>");
	out.print("<td>" + metadata.isSearchable(i) + "</td>");
	out.print("<td>" + metadata.isNullable(i) + "</td></tr>");
}

%>

</table>
</section>


<section>
<h3>Results</h3>
<table>
<%

	out.print("<tr>");
	for (int i=1; i <= metadata.getColumnCount(); i++) {
		out.print("<th>" + metadata.getColumnName(i) + "</th>");
	}
	out.print("</tr>");		
	
	int y=1;
	while(results.next()){
		out.print("<tr>");
		for (int i=1; i <= metadata.getColumnCount(); i++) {
			out.print("<td>" + results.getObject(metadata.getColumnName(i)).toString() + "</td>");
		}		
		out.print("</tr>");
		y++;
	}
	

} catch (SQLException ex) {
	ex.printStackTrace();
}
%>
</table>
</section>
<% } %>

<footer>
<a href="index.jsp">Return to home</a>
</footer>
</body>
</html>