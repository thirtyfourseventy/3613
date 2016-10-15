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


		<table>
			<tr>
				<th>number</th><th>name</th><th>brew_date</th><th>grist</th><th>hops</th><th>water</th>
				<th>yeast</th><th>yeast_code</th><th>pitching_temp</th><th>ferment_temp</th><th>og</th>
			</tr>

			<%
				List<BrewingRecord> records = (ArrayList<BrewingRecord>) request.getAttribute("records");

				for (BrewingRecord record : records) {
			%>
			<tr>
				<form METHOD="POST" ACTION="assignment1">
				<td><input type="text" name="number"
					value="<%out.print(record.getNumber());%>">
					<input type="hidden" name="uidpk"
					value="<%out.print(record.getUidpk());%>"></td>
				<td><input type="text" name="name"
					value="<%out.print(record.getName());%>"></td>
				<td><input type="text" name="brew_date"
					value="<%out.print(record.getBrew_date());%>"></td>
				<td><input type="text" name="grist"
					value="<%out.print(record.getGrist());%>"></td>
				<td><input type="text" name="hops"
					value="<%out.print(record.getHops());%>"></td>
				<td><input type="text" name="water"
					value="<%out.print(record.getWater());%>"></td>
				<td><input type="text" name="yeast"
					value="<%out.print(record.getYeast());%>"></td>
				<td><input type="text" name="yeast_code"
					value="<%out.print(record.getYeast_code());%>"></td>
				<td><input type="text" name="pitching_temp"
					value="<%out.print(record.getPitching_temp());%>"></td>
				<td><input type="text" name="ferment_temp"
					value="<%out.print(record.getFerment_temp());%>"></td>
				<td><input type="text" name="og"
					value="<%out.print(record.getOg());%>"></td>
				<td><input type="text" name="fg"
					value="<%out.print(record.getFg());%>"></td>
				<td><input type="text" name="abv"
					value="<%out.print(record.getAbv());%>"></td>
				<td><input type="text" name="package_date"
					value="<%out.print(record.getPackage_date());%>"></td>
				<td><input type="text" name="notes"
					value="<%out.print(record.getNotes());%>"></td>
				<td><input type="submit" name="action" value="Update">
				</td>
				<td><input type="submit" name="action" value="Delete">
				</td>
				</form>
			</tr>
			<%
				}
			%>
			<tr>
				<form METHOD="POST" ACTION="assignment1">
				<td><input type="text" name="number" placeholder="number">
				</td>
				<td><input type="text" name="name" placeholder="name">
				</td>
				<td><input type="text" name="brew_date" placeholder="brew_date">
				</td>
				<td><input type="text" name="grist" placeholder="grist">
				</td>
				<td><input type="text" name="hops" placeholder="hops">
				</td>
				<td><input type="text" name="water" placeholder="water">
				</td>
				<td><input type="text" name="yeast" placeholder="yeast">
				</td>
				<td><input type="text" name="yeast_code"
					placeholder="yeast_code"></td>
				<td><input type="text" name="pitching_temp"
					placeholder="pitching_temp"></td>
				<td><input type="text" name="ferment_temp"
					placeholder="ferment_temp"></td>
				<td><input type="text" name="og" placeholder="og"></td>
				<td><input type="text" name="fg" placeholder="fg"></td>
				<td><input type="text" name="abv" placeholder="abv"></td>
				<td><input type="text" name="package_date"
					placeholder="package_date"></td>
				<td><input type="text" name="notes" placeholder="notes">
				</td>
				<td><input type="submit" name="action" value="Create">
				</td>
				</form>
			</tr>

		</table>
	
</body>
</html>