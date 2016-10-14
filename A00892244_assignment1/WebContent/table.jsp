<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.util.*"%>
<%@page import="a00892244.assignment1.data.BrewingRecord"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel='stylesheet' type='text/css' href='assets/styles.css'>
</head>
<body>

<header>
<h1>Brewing Records</h1>
</header>

<section>

<% 
	List<BrewingRecord> records = (ArrayList<BrewingRecord>)request.getAttribute("records");
	for(BrewingRecord record : records) {  %>
	<form METHOD="POST" ACTION="assignment1">
			<input type="text" name="number" value="<%out.print(record.getNumber());%>" disabled>
			<input type="text" name="number" value="<%out.print(record.getNumber());%>" style = "display:none">
			<input type="text" name="name" value="<%out.print(record.getName());%>">
			<input type="text" name="brew_date" value="<%out.print(record.getBrew_date());%>">
			<input type="text" name="grist" value="<%out.print(record.getGrist());%>">
			<input type="text" name="hops" value="<%out.print(record.getHops());%>">
			<input type="text" name="water" value="<%out.print(record.getWater());%>">
			<input type="text" name="yeast" value="<%out.print(record.getYeast());%>">
			<input type="text" name="yeast_code" value="<%out.print(record.getYeast_code());%>">
			<input type="text" name="pitching_temp" value="<%out.print(record.getPitching_temp());%>">
			<input type="text" name="ferment_temp" value="<%out.print(record.getFerment_temp());%>">
			<input type="text" name="og" value="<%out.print(record.getOg());%>">
			<input type="text" name="fg" value="<%out.print(record.getFg());%>">
			<input type="text" name="abv" value="<%out.print(record.getAbv());%>">
			<input type="text" name="package_date" value="<%out.print(record.getPackage_date());%>">
			<input type="text" name="notes" value="<%out.print(record.getNotes());%>">
			<input type="submit" name="action" value="Update"> 


	
	
	<input type="submit" name="action" value="Delete">
	</form>
	<%
	}

%>
		<form METHOD="POST" ACTION="assignment1">
			<input type="text" name="number" placeholder="number">
			<input type="text" name="name" placeholder="name">
			<input type="text" name="brew_date" placeholder="brew_date">
			<input type="text" name="grist" placeholder="grist">
			<input type="text" name="hops" placeholder="hops">
			<input type="text" name="water" placeholder="water">
			<input type="text" name="yeast" placeholder="yeast">
			<input type="text" name="yeast_code" placeholder="yeast_code">
			<input type="text" name="pitching_temp" placeholder="pitching_temp">
			<input type="text" name="ferment_temp" placeholder="ferment_temp">
			<input type="text" name="og" placeholder="og">
			<input type="text" name="fg" placeholder="fg">
			<input type="text" name="abv" placeholder="abv">
			<input type="text" name="package_date" placeholder="package_date">
			<input type="text" name="notes" placeholder="notes">
			<input type="submit" name="action" value="Create"> 
		</form>


</section>
</body>
</html>