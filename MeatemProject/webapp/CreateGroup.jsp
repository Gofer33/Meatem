<?xml version="1.0" encoding="UTF-8" ?>
<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
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
    <H1>Zakladanie grupy!</H1>

    
    <BR>
   	 <FORM ACTION="CreateGroup" METHOD="POST">
    	Wpisz nazwę grupy: <BR> <TEXTAREA NAME="name"
                ROWS="1"></TEXTAREA> <BR>   
                <input type="hidden" name="userName" value="<%=session.getAttribute("user") %>" />
         <BR> <INPUT TYPE="SUBMIT" VALUE="Zaloz">
    </FORM>
</body>
</html>