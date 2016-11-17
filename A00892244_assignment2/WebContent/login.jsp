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


<%@page import="java.util.*"%>

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


<body>

	<header>
		<h1><%=resourceBundle.getString("Project_Name")%></h1>
		<a class="lang" id="en" href="assignment2">English</a>  <a class="lang" id="fr" href="assignment2">French</a> <a class="lang" id="lc" href="assignment2">lolcat</a>
		<script>
			$(".lang").click(function () {
				document.cookie = "language=" + $(this).attr("id");
			});
		
		</script>
		
		<form id="logoutform" METHOD="POST" ACTION="assignment2">
		<button type="submit" name="action" value="logout">logout</button>
	</header>

<section>
<form id="loginform" METHOD="POST" ACTION="assignment2">
	<input type="text" name="password" value="password"
						title="name is required" required
						maxlength="64" size="32">
	<button type="submit" name="action" value="login">login</button>

</form>
</section>

	<footer>
	<h4>Edward Lambke A00892244 <%=resourceBundle.getString("Assignment_Title")%></h4>
	<a href="index.jsp"><%=resourceBundle.getString("Return_To_Homepage_Link")%></a>
	</footer>

</body>
</html>