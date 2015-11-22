<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-2"%>
<%@page import="com.journaldev.first.Utitlities" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="ErrorMessage" scope="request" class="com.journaldev.first.ErrorMessageBean" />
<html>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-2">
<title>Formularz Rejestracji</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div id=container>
		<h1>Formularz rejestracji</h1>
		
		<form action=Servlet1 method="get">
			<div id="etykiety">
				Imię:</br>
				Nazwisko:</br>
				PESEL:</br>
				Email:</br>
				Zawód:</br>
			</div>
			<div id="pola">
				<input type="text" name="imie" maxlength="30" placeholder="First Name" /><br/>
				<input type="text" name="nazwisko" maxlength="30" placeholder="Last Name"/><br/>
				<input type="text" name="pesel" maxlength="11 " placeholder="99999999999"/><br/>
				<input type="text" name="email" maxlength="40" placeholder="email@example.com"/><br/>
			
				<select id="zawod" name="zawod">
		 			<option value=1>Pracownik IT</option>
		  			<option value=2>Nauczyciel</option>
		  			<option value=3>Lekarz</option>
				</select><br/>
			</div>
			<div style="clear:both"></div>
			<input type="submit" />
		</form>
		
		
		<p id="errorMsg"><jsp:getProperty name="ErrorMessage" property="message" /><p>
		
		<br/><h3>Zarejestrowani:</h3>
		<table>
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Pesel</th>
			<th>Email</th>
			<th>Occupation</th>
			
		</tr>
		<%=Utitlities.Write()%>
		</table>
	</div>


	
</body>
</html>