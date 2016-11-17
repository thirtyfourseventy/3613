<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.util.*"%>
<%@page import="a00892244.assignment2.data.BrewingRecord"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>A00892244 Edward Lambke</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel='stylesheet' type='text/css' href='assets/styles.css'>
<script src="assets/jquery.min.js"></script>
<script src="assets/jquery-ui.min.js"></script>

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

<script>
  $( function() {
	  $("#accordion").accordion({ header: "h3", collapsible: true, active: false } );
  }); 
  
  /*  NOT WORKING WITH ACCORDION
  $( function() {
	    $( "#datepicker" ).datepicker({dateFormat:'mm/dd/yy'} 
	    $('#ui-datepicker-div').css('clip', 'auto' ):
	  }  
*/
  </script>
  

</head>
<body>

	<header>
		<h1><%=resourceBundle.getString("Project_Name")%></h1>
		<a class="lang" id="en" href="assignment2">English</a>  <a class="lang" id="fr" href="assignment2">French</a> <a class="lang" id="lc" href="assignment2">lolcat</a>
		<script>
			$(".lang").click(function () {
				document.cookie = "language=" + $(this).attr("id");
			});
		
		</script>
	</header>



	<div id="accordion">
		<%
			List<BrewingRecord> records = (ArrayList<BrewingRecord>) request.getAttribute("records");

			for (BrewingRecord record : records) {
		%>


		<h3>
			#<%=record.getNumber() %>
			<em>***<%=record.getName() %> ***
			</em> 
			<% if (record.getBrew_date().trim().length() > 0) {
				out.print(" brewed: " + record.getBrew_date() );
			}
			%>
			
			<% if (record.getOg() > 0) {
				out.print(" OG: " + record.getOg() );
			}
			%>
			
			<% if (record.getAbv() > 0) {
				out.print("ABV: " + record.getAbv() + "%"); 
				}
			%>
			

		</h3>

		<div>

			<form id="edit_form_<%=record.getUidpk() %>" METHOD="POST" ACTION="assignment2">
				<input type="hidden" name="uidpk"
					value="<%=record.getUidpk() %>">

				<div class="form_fields">
					<h4>Batch #</h4>
					<input type="text" name="number"
						value="<%=record.getNumber() %>" pattern="[0-9]{1,4}"
						title="####" required maxlength="4" size="4">
				</div>
				<div class="form_fields">
					<h4>Name</h4>
					<input type="text" name="name"
						value="<%=record.getName() %>"
						pattern="[\s\S]*\S[\s\S]*" title="name is required" required
						maxlength="64" size="32">
				</div>
				<div class="form_fields">
					<h4>Brew Date</h4>
					<input type="text" name="brew_date" id="datepicker"
						value="<%=record.getBrew_date() %>" maxlength="64"
						size="12">
				</div>
				<div class="form_fields">
					<h4>Grist</h4>
					<textarea name="grist" form="edit_form_<%=record.getUidpk() %>" maxlength="512" cols="64"
						rows="8"><%=record.getGrist() %></textarea>
				</div>
				<div class="form_fields">
					<h4>Hops</h4>
					<textarea name="hops" form="edit_form_<%=record.getUidpk() %>" maxlength="512" cols="64"
						rows="8"><%=record.getHops() %></textarea>
				</div>
				<div class="form_fields">
					<h4>Water</h4>
					<input type="text" name="water"
						value="<%=record.getWater() %>" maxlength="64" size="64">
				</div>
				<div class="form_fields">
					<h4>Yeast</h4>
					<input type="text" name="yeast"
						value="<%=record.getYeast() %>" maxlength="64" size="32">
				</div>
				<div class="form_fields">
					<h4>Yeast Code</h4>
					<input type="text" name="yeast_code"
						value="<%=record.getYeast_code() %>" maxlength="64"
						size="32">
				</div>
				<div class="form_fields">
					<h4>Pitching Temp</h4>
					<input type="text" name="pitching_temp"
						value="<%=record.getPitching_temp() %>" maxlength="64"
						size="12">
				</div>
				<div class="form_fields">
					<h4>Ferment Temp</h4>
					<input type="text" name="ferment_temp"
						value="<%=record.getFerment_temp() %>" maxlength="64"
						size="12">
				</div>
				<div class="form_fields">
					<h4>Original Gravity</h4>
					<input type="text" name="og" value="<%=record.getOg() %>"
						pattern="[0,1](?:\.[0-9]{1,3})?" title="#.###, typically between 1.000 and 1.100" maxlength="6"
						size="8">
				</div>
				<div class="form_fields">
					<h4>Final Gravity</h4>
					<input type="text" name="fg" value="<%=record.getFg() %>"
						pattern="[0,1](?:\.[0-9]{1,3})?" title="#.###, typically between 1.000 and 1.100" maxlength="6"
						size="8">
				</div>
				<div class="form_fields">
					<h4>% ABV</h4>
					<input type="text" name="abv"
						value="<%=record.getAbv() %>"
						pattern="[0-9]*(?:\.[0-9]{0,3})?" title="##.###" maxlength="8"
						size="8" disabled>
				</div>
				<div class="form_fields">
					<h4>Package Date</h4>
					<input type="text" name="package_date" id="datepicker"
						value="<%=record.getPackage_date() %>" maxlength="64">
				</div>
				<div class="form_fields">
					<h4>Notes</h4>
					<textarea name="notes" form="edit_form_<%=record.getUidpk() %>" maxlength="512" cols="64"
						rows="8"><%=record.getNotes() %></textarea>
					<hr />
					
					<button type="submit" name="action" value="Update"><%=resourceBundle.getString("Update_Button")%></button>
					<button type="submit" name="action" value="Delete"><%=resourceBundle.getString("Delete_Button")%></button>

				
				</div>
			</form>


		</div>

		<%
			}
		%>
		

		<h3><%=resourceBundle.getString("Add_New_Batch_Title")%></h3>
		<div>

			<form id="new_batch_form" METHOD="POST" ACTION="assignment2">
				<div class="form_fields">
					<h4>Batch #</h4>
					<input type="text" name="number" pattern="[0-9]{1,4}" title="####"
						required maxlength="4" size="4">
				</div>
				<div class="form_fields">
					<h4>Name</h4>
					<input type="text" name="name" pattern="[\s\S]*\S[\s\S]*"
						title="name is required" required maxlength="64" size="32">
				</div>
				<div class="form_fields">
					<h4>Brew Date</h4>
					<input type="text" name="brew_date" id="datepicker" maxlength="64"
						size="12">
				</div>
				<div class="form_fields">
					<h4>Grist</h4>
					<textarea name="grist" form="new_batch_form" maxlength="512"
						cols="64" rows="8"></textarea>
				</div>
				<div class="form_fields">
					<h4>Hops</h4>
					<textarea name="hops" form="new_batch_form" maxlength="512"
						cols="64" rows="8"></textarea>
				</div>
				<div class="form_fields">
					<h4>Water</h4>
					<input type="text" name="water" maxlength="64" size="64">
				</div>
				<div class="form_fields">
					<h4>Yeast</h4>
					<input type="text" name="yeast" maxlength="64" size="32">
				</div>
				<div class="form_fields">
					<h4>Yeast Code</h4>
					<input type="text" name="yeast_code" maxlength="64" size="32">
				</div>
				<div class="form_fields">
					<h4>Pitching Temp</h4>
					<input type="text" name="pitching_temp" maxlength="64" size="12">
				</div>
				<div class="form_fields">
					<h4>Ferment Temp</h4>
					<input type="text" name="ferment_temp" maxlength="64" size="12">
				</div>
				<div class="form_fields">
					<h4>Original Gravity</h4>
					<input type="text" name="og" pattern="[0,1](?:\.[0-9]{1,3})?"
						title="#.###, typically between 1.000 and 1.100" maxlength="6" size="8">
				</div>
				<div class="form_fields">
					<h4>Final Gravity</h4>
					<input type="text" name="fg" pattern="[0,1](?:\.[0-9]{1,3})?"
						title="#.###, typically between 1.000 and 1.100" maxlength="6" size="8">
				</div>
				<div class="form_fields">
					<h4>% ABV</h4>
					<input type="text" name="abv" pattern="[0-9]*(?:\.[0-9]{0,3})?"
						title="#.###, typically between 1.000 and 1.100" maxlength="8" size="8" disabled placeholder="calculated">
				</div>
				<div class="form_fields">
					<h4>Package Date</h4>
					<input type="text" name="package_date" id="datepicker"
						maxlength="64">
				</div>
				<div class="form_fields">
					<h4>Notes</h4>
					<textarea name="notes" form="new_batch_form" maxlength="512"
						cols="64" rows="8"></textarea>
					<hr />
					<button type="submit" name="action" value="Create"><%=resourceBundle.getString("Create_Button")%></button>
				</div>
			</form>


		</div>
	</div>
	
	<footer>
	<h4>Edward Lambke A00892244 <%=resourceBundle.getString("Assignment_Title")%></h4>
	<a href="summary.jsp"><%=resourceBundle.getString("Summary_Page")%></a>
	<a href="assignment2"><%=resourceBundle.getString("Return_To_Homepage_Link")%></a>
	</footer>
</body>

<script>
	
$("button[value=Delete]").click(function(event){
	if(!confirm('<%=resourceBundle.getString("Delete_Confirmation")%>'))
		event.preventDefault(); 
}); 

</script>





<%
	if (request.getAttribute("error") != null) {
%>
<script type="text/javascript">
alert("<%=request.getAttribute("error") %>" );
</script>
<%
	}
%>


</html>