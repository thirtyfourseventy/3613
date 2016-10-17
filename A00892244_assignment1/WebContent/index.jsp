<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>A00892244 Edward Lambke</title>
<link rel='stylesheet' type='text/css' href='assets/styles.css'>
</head>
<body>


<header>
<h1>Edward Lambke A00892244</h1>
</header>

<section>
<h1>Assignment 1</h1>

<h2>Homebrewing Records Database </h2>

<FORM METHOD="POST" ACTION="assignment1">
<input type="hidden" name="access_type" value="read_write">
<input type="submit" name="action" value="Login for read write access">
</FORM>

<FORM METHOD="POST" ACTION="assignment1">
<input type="submit" name="action" value="Enter with read only access">
</FORM>

<FORM METHOD="POST" ACTION="assignment1">
<input type="submit" name="action" value="Logout of Basic Auth">
</FORM>
</section>

<footer id="disclaimer">
<h3>Project Notes:</h3>
This is a database version of an excel spreadsheet I have for documenting homebrewed beer.  
I have only entered the last 5 batches in as test data.

<h4>This page</h4>
To satisfy the use of headers requirement a rudimentary authentication functionality was added.
<ul><li>To sign in with read/write privileges use username/password: admin/admin.  This will allow reading, updating and deleting from the DB
</li><li>Read only has updating and deleting functionality disabled.  Return to the index page to switch modes.
</li><li>The Logout button is barely functional and was added as a workaround for testing.  Click it, cancel out and return to the homepage 
if you want to reauthenticate.
</li>
<li>The login functionality does not work well with the eclipse browser.  Please use FF or Chrome.</li></ul>

<h4>The DB</h4>
<ul>
<li>The primary key is a guid that is not visible or editable from the UI. The batch number is not a primary key because I sometimes split a 
batch and ferment with different yeast, as such my spread sheet has muliple rows with the same number.  
</li><li>The ABV column is not editable as Alcohol by Volume is calculated based on the Original and Final Gravities.
 </li>
 <li>To drop, recreate the DB and populate with test data, run DataManager.java as Java Application.
 </ul>

<h4>The table view</h4>
The 15 columns and several large fields made a simple table view useless so I quickly switched to the accordion jqueryui to hide the form fields.
<ul>
<li>There are some layout issues with the text fields in the expanded accordions.</li>
<li>The date fields originally used the jqueryui datepicker but it didn't play well with the accordion and has been disabled.</li>
<li>Reloading the table page can cause bad things to happen.</li>
<li>This has been tested on FF and Chrome.</li>
</ul> 




</footer>

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