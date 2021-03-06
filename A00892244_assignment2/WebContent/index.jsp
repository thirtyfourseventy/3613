<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import="java.util.*"%>
    <%@page import="a00892244.assignment2.localization.*"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>A00892244 Edward Lambke</title>
<link rel='stylesheet' type='text/css' href='assets/styles.css'>
<script src="assets/jquery.min.js"></script>
</head>
<body>

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
  if (language.equals("es")) {
	  locale =  new Locale("es", "ES");
  }
}

ResourceBundle resourceBundle = ResourceBundle.getBundle("a00892244.assignment2.localization.IndexResource", locale);
%>

<header>
<h1>Edward Lambke A00892244</h1>
<a class="lang" id="en" href="index.jsp">English</a>  <a class="lang" id="fr" href="index.jsp">Fran�ais</a> <a class="lang" id="es" href="index.jsp">Espa�ol</a>
<script>
$(".lang").click(function () {
	document.cookie = "language=" + $(this).attr("id");
});

</script>
</header>

<section>
<h1><%=resourceBundle.getString("Assignment_Title")%></h1>

<h2><%=resourceBundle.getString("Project_Name")%></h2>

<a href="assignment2"><%=resourceBundle.getString("Read_Write_Access")%></a>

</section>

<section class="instructions">
<h3><%=resourceBundle.getString("Instructions_Title")%></h3>

<h4><%=resourceBundle.getString("Index_Page_Instructions_Title")%></h4>

<ul><li><%=resourceBundle.getString("Read_Write_Auth_Instructions")%></li>
</ul>

<h4><%=resourceBundle.getString("DB_Page_Instructions_Title")%></h4>

<ul><li><%=resourceBundle.getString("Primary_Key_Instructions")%></li>
<li><%=resourceBundle.getString("ABV_Instructions")%></li>
<li><%=resourceBundle.getString("Create_Instructions")%></li>
<li><%=resourceBundle.getString("Reload_Limitation")%></li></ul>
</section>


</body>

<%
	if (request.getAttribute("error") != null) {
%>
<script type="text/javascript">
alert("<%out.print(request.getAttribute("error"));%>");
</script>
<%
	}
%>

</html>