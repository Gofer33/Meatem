<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd%22%3E
<html xmlns="http://www.w3.org/1999/xhtml%22%3E
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Index</title>
</head>
<body>
<%
//allow access only if session exists
if(session.getAttribute("user") == null){
	response.sendRedirect("SignIn.jsp");
}
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user")) userName = cookie.getValue();
}
}
%>
<h3>Czesc <%=session.getAttribute("user") %>!</h3>
    <H1>Zmien swoje dane!</H1>
  
    	<FORM ACTION="userdetails" METHOD="POST">
    	Imie: <BR>	  <input type="text" NAME="first_name"ROWS="1"> <BR>
        Nazwisko:<BR> <input type="text" NAME="last_name" ROWS="1"> <BR> 
        Wiek:<BR> <input type="text" NAME="age"> <BR> 
        Plec:<BR> <input type="radio" name="gender" value="Mezczyzna" checked> Mezczyzna<br>
  				<input type="radio" name="gender" value="Kobieta"> Kobieta <br>
        Email: <BR><input type="email" NAME="email" ROWS="1"> <BR> 
        Telefon: <BR><input type="text" NAME="phone" ROWS="1"> <BR> 
        Kraj:<BR> <input type="text" NAME="country" ROWS="1"> <BR> 
        Miejscowosc:<BR> <input type="text" NAME="city" ROWS="1"> <BR> 
        <input type="hidden" name="userName" value="<%=userName %>" />
        <INPUT TYPE="SUBMIT" VALUE="Wyślij">
    </FORM>
</body>
</html>