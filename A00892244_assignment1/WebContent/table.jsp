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
				<th>yeast</th><th>yeast_code</th><th>pitching_temp</th><th>ferment_temp</th><th>og</th><th>fg</th><th>abv</th>
				<th>package_date</th><th>notes</th>
			</tr>

			<%
				List<BrewingRecord> records = (ArrayList<BrewingRecord>) request.getAttribute("records");

				for (BrewingRecord record : records) {
			%>
			<tr>
				<form METHOD="POST" ACTION="assignment1">
				<td><input type="text" name="number"
					value="<%out.print(record.getNumber());%>" pattern="[0-9]{1,4}" title="####" required maxlength="4">
					<input type="hidden" name="uidpk"
					value="<%out.print(record.getUidpk());%>"></td>
				<td><input type="text" name="name"
					value="<%out.print(record.getName());%>" pattern="[\s\S]*\S[\s\S]*" title="name is required" required maxlength="64"></td>
				<td><input type="text" name="brew_date"
					value="<%out.print(record.getBrew_date());%>" maxlength="64"></td>
				<td><input type="text" name="grist"
					value="<%out.print(record.getGrist());%>" maxlength="512"></td>
				<td><input type="text" name="hops"
					value="<%out.print(record.getHops());%>" maxlength="512"></td>
				<td><input type="text" name="water"
					value="<%out.print(record.getWater());%>" maxlength="64"></td>
				<td><input type="text" name="yeast"
					value="<%out.print(record.getYeast());%>" maxlength="64"></td>
				<td><input type="text" name="yeast_code"
					value="<%out.print(record.getYeast_code());%>" maxlength="64"></td>
				<td><input type="text" name="pitching_temp"
					value="<%out.print(record.getPitching_temp());%>" maxlength="64"></td>
				<td><input type="text" name="ferment_temp"
					value="<%out.print(record.getFerment_temp());%>" maxlength="64"></td>
				<td><input type="text" name="og"
					value="<%out.print(record.getOg());%>" pattern="[0,1](?:\.[0-9]{1,3})?" title="#.###" maxlength="6"></td>
				<td><input type="text" name="fg"
					value="<%out.print(record.getFg());%>" pattern="[0,1](?:\.[0-9]{1,3})?" title="#.###" maxlength="6"></td>
				<td><input type="text" name="abv"
					value="<%out.print(record.getAbv());%>" pattern="[0-9]*(?:\.[0-9]{0,3})?" title="##.###" maxlength="8"></td>
				<td><input type="text" name="package_date"
					value="<%out.print(record.getPackage_date());%>" maxlength="64"></td>
				<td><input type="text" name="notes"
					value="<%out.print(record.getNotes());%>" maxlength="512"></td>
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
				<td><input type="text" name="number" placeholder="number" pattern="[0-9]{1,4}" title="####" required maxlength="4">
				</td>
				<td><input type="text" name="name" placeholder="name" pattern="[\s\S]*\S[\s\S]*" title="name is required" required maxlength="64">
				</td>
				<td><input type="text" name="brew_date" placeholder="brew_date" maxlength="64">
				</td>
				<td><input type="text" name="grist" placeholder="grist" maxlength="512">
				</td>
				<td><input type="text" name="hops" placeholder="hops" maxlength="512">
				</td>
				<td><input type="text" name="water" placeholder="water" maxlength="64">
				</td>
				<td><input type="text" name="yeast" placeholder="yeast" maxlength="64">
				</td>
				<td><input type="text" name="yeast_code"
					placeholder="yeast_code" maxlength="64"></td>
				<td><input type="text" name="pitching_temp"
					placeholder="pitching_temp" maxlength="64"></td>
				<td><input type="text" name="ferment_temp"
					placeholder="ferment_temp" maxlength="64"></td>
				<td><input type="text" name="og" placeholder="og" pattern="[0,1](?:\.[0-9]{1,3})?" title="#.###" maxlength="6"></td>
				<td><input type="text" name="fg" placeholder="fg" pattern="[0,1](?:\.[0-9]{1,3})?" title="#.###" maxlength="6"></td>
				<td><input type="text" name="abv" placeholder="abv" pattern="[0-9]{1,2}(?:\.[0-9]{1,3})?" title="##.###" maxlength="8"></td>
				<td><input type="text" name="package_date"
					placeholder="package_date" maxlength="64"></td>
				<td><input type="text" name="notes" placeholder="notes" maxlength="512">
				</td>
				<td><input type="submit" name="action" value="Create">
				</td>
				</form>
			</tr>

		</table>
	
</body>

<%
if(request.getAttribute("error")!=null){
	%>
	<script>alert("<%out.print(request.getAttribute("error")); %>")</script>
	
	<%
}
%>

</html>