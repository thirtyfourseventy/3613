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

<%
String query = "";
Cookie[] cookies = request.getCookies();
if (cookies != null) { 
  Cookie cookie;
  for(int i=0; i<cookies.length; i++) {
    cookie = cookies[i];
    if (cookie.getName().equals("query")) {
   		query = cookie.getValue().toString();
  	}
  }
}
%>
<header><h1>Edward Lambke A00892244 Lab7</h1></header>

<section>
<form METHOD="POST" ACTION="lab07">
Enter db query:
	<input type="text" name="query" size=64 value="<%=java.net.URLDecoder.decode(query, "UTF-8") %> ">
	<input type="submit" value="Submit"> 
</form>
</section>

<%
	if (request.getAttribute("error") != null) {
%>
<script type="text/javascript">
alert("<%out.print(request.getAttribute("error"));%>");
</script>
<%
	}
%>

</body>
</html>