<!DOCTYPE html>

<html>
<head>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META name="GENERATOR" content="IBM Software Development Platform">
	<META http-equiv="Content-Style-Type" content="text/css">
	<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>

	<style>
		body
		{
			background-image:url('${pageContext.request.contextPath}/images/back.png');
			background-repeat:repeat;
			margin: 0;
			padding: 0;
		}

		.content
		{
			margin-left: auto;
			margin-right: auto;
			width: 1025px;
			height: 900px;
			background-image:url('${pageContext.request.contextPath}/images/centerBack.png');
			background-repeat: repeat-y;
		}

		.logo
		{
			background-image:url('${pageContext.request.contextPath}/images/Cat_logo.gif');
			background-repeat: no-repeat;
			background-position: center;
			width: 75px;
			height: 95px;
			padding-left: 75px;
		}
		.form
		{
			font-family: 'Open Sans', sans-serif;
			width: 400px;
			height: 400px;
			margin-right:auto;
			margin-left:auto;
			margin-top: auto;
			margin-bottom: auto;
		}
		.title
		{
			font-family: 'Open Sans', sans-serif;
			font-family: 'Open Sans', sans-serif;
			font-size: 36px;
			width: 400px;
			height: 75px;
			margin-right:auto;
			margin-left:auto;
			margin-top: auto;
			margin-bottom: auto;
		}
		.button
		{
			font-family: 'Open Sans', sans-serif;
			font-size: 20px;
			margin-left: 120px;
			margin-top: 15px;
			border: none;
			background-color: white;
			background-image:url('${pageContext.request.contextPath}/images/ButtonUnclk.png');
			background-repeat: no-repeat;
			width: 156px;
			height: 83px;
		}
		.button:hover
		{
			border: none;
			background-color: white;
			background-image:url('${pageContext.request.contextPath}/images/ButtonClk.png');
			background-repeat: no-repeat;
		}
		.error
		{
			font-size: 25px;
			text-align: center;
			font-family: 'Open Sans', sans-serif;
			color: red;
		}
		
		.success
		{
			font-size: 25px;
			text-align: center;
			font-family: 'Open Sans', sans-serif;
			color: green;
		}
		.help
		{
			width: 20px;
			height: 20px;
		}
		.help_text
		{
			display: none;
			width: 400px;
			height: 400px;
			text-align:left;
		}
		.help_button
		{
			width:20px;
			height:20px;
			border: none;
			background-image:url('${pageContext.request.contextPath}/images/help.png');
			background-repeat: no-repeat;
		}
		.help_button:hover
		{
			cursor:help;
		}
		.highlight
		{
			font-weight:bold;
			font-size: 16px;
			font-family: 'Open Sans', sans-serif;
			color: green;
		}

	</style>
</head>

<body>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ page import="java.text.SimpleDateFormat;" %>

<%
	Date today = new Date();
	Calendar cal = new GregorianCalendar();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	cal.setTime(today);
	cal.add(Calendar.DAY_OF_MONTH, -90);
	String today90 = df.format(cal.getTime());
 %>
 <script type="text/javascript">
 	function showDiv() 
 	{
	   document.getElementById('helpMe').style.display = "block";
	}
 </script>

 
	<div class= "content">
		<div class= "logo">
		</div>
		<div class="title">
				<p>PI Data Integration</p>
		</div>
		<div class= "form">
			<div class = "error">
			<jsp:useBean id="errorMessage" scope="request" class="java.lang.String"></jsp:useBean><%=errorMessage%>
			</div>
			<HR>
			<P></P>
			<FORM method="post" action="${pageContext.request.contextPath}/AssetRetrievalServlet" name="LoginForm">
				<table>
					<tbody>
						<tr>
							<td>
								<label>Asset Number</label>
							</td>
							<td>
				        		<INPUT type="text" name="asset" size="20"><br>
				        	</td>
				        	<td>
				        		<div class="help">
				        			<input type="button" class="help_button" name="help" onclick="showDiv()" />	
				        		</div>
				        	</td>
				        </tr>

				        <tr>
				        	<td>
				        		<label>Data newer than</label>
				        	</td>
				        	<td>
				       			<INPUT type="date" name="date" value=<%=today90%>>
				       		</td>
				       	</tr>
				    </tbody>
				</table>
			        <INPUT class="button" type="submit" name="submit" value="Send to Pi">
			    <hr>
			</FORM>
			<div class = "success">
			<jsp:useBean id="successMessage" scope="request" class="java.lang.String"></jsp:useBean><%=successMessage%>
			</div>
				<div class="help_text" id="helpMe">
					<p>
					The asset field needs to be filled in with a<span class="highlight"> valid </span>
					serial number.
					<br>
					<br>
					Data can be no older than<span class="highlight"> 1 year</span>.<br>
					The default date is<span class="highlight"> 90 days </span>prior to today.<br>
					The date needs to be in the format<span class="highlight"> yyyy/mm/dd</span>.
					<br>
					<br>
					Once the above information is valid, and submitted, 
					the matching assets will be transferred to Pi in a 
					throttled manner. 
					<br>
					<br>
					When the transfer is complete a success message will be
					displayed. Otherwise an error message is displayed.
					</p>
			</div>
		</div>
	</div>
</body>
</html>