<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edward Lambke A00892244</title>
<link rel='stylesheet' type='text/css' href='assets/styles.css'>
<script src="assets/jquery.min.js"></script>
</head>
<body>

<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>

<%
Locale locale = Locale.ENGLISH;

String language = "";
Cookie[] cookies = request.getCookies();
if (cookies != null) { 
  Cookie cookie;
  for(int i=0; i<cookies.length; i++) {
    cookie = cookies[i];
    if (cookie.getName().equals("language")) {
    	language = cookie.getValue().toString();
  	}
  }
  if (language.equals("fr")) {
	  locale = locale.FRENCH;
  }
  if (language.equals("lc")) {
	  locale = new Locale.Builder().setLanguage("lc").build();
  }
}

ResourceBundle resourceBundle = ResourceBundle.getBundle("a00892244.assignment2.localization.TableResource", locale);

%>


</head>
<body>

	<header>
		<h1><%=resourceBundle.getString("Project_Name")%></h1>
		<a class="lang" id="en" href="summary.jsp">English</a>  <a class="lang" id="fr" href="summary.jsp">French</a>   <a class="lang" id="lc" href="summary.jsp">lolcat</a>
		<script>
			$(".lang").click(function () {
				document.cookie = "language=" + $(this).attr("id");
			});
		
		</script>
	</header>
<section>
<h2><%=resourceBundle.getString("Query_History")%></h2>

<ol>
<% if(session.getAttribute("queries") != null) { 
	List<String> queries = (ArrayList<String>)session.getAttribute("queries");

	for(int i =0; i < queries.size(); i++) {
		out.print("<li>" + queries.get(i) + "</li>");
	}
}
%>

</ol>
</section>

	<footer>
	<h4>Edward Lambke A00892244 <%=resourceBundle.getString("Assignment_Title")%></h4>
    <a href="index.jsp"><%=resourceBundle.getString("Return_To_Homepage_Link")%></a>
	</footer>
</body>

</html>