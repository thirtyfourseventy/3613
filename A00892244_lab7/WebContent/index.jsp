<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>A00892244 Lab7</title>
<link rel='stylesheet' type='text/css' href='assets/styles.css'>
</head>
<body>

<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>

<%
String query = "";
Cookie[] cookies = request.getCookies();
if (cookies == null) {
  out.println("<TR><TH COLSPAN=2>No cookies");
} else {
  Cookie cookie;
  for(int i=0; i<cookies.length; i++) {
    cookie = cookies[i];
    if (cookie.getName().equals("query")) {
   		query = cookie.getValue().toString();
  	}
  }
}
%>
<header>A00892244 Lab7</header>

<section>
<form METHOD="POST" ACTION="lab07">
Enter db query:
	<input type="text" name="query" value="<%=java.net.URLDecoder.decode(query, "UTF-8") %>">
	<input type="submit" value="Submit"> 
</form>
</section>


</body>
</html>