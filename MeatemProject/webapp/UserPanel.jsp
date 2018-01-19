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
 <%
    try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = 
				DriverManager.getConnection
            ("jdbc:mysql://localhost/announcements?user=root&password=supersilnehaslo123");

		Statement statement = connection.createStatement() ;
		String query = "select * from user_details INNER JOIN users ON users.User_ID = user_details.User_ID WHERE"
	    		   + " users.Login = '" + userName+"';";
	    		   System.out.println("[TEST] " + query);
       resultset = statement.executeQuery(query);
%>  
  <%
  while(resultset.next()){ 
	  String firstName = "";
	  %>  
	  Imie: <%= resultset.getString("First_Name") %><br>
	  Nazwisko: <%= resultset.getString("Last_Name") %><br>
	  Wiek: <%= resultset.getString("Age") %><br>
	  Plec: <%= resultset.getString("Sex") %><br>
	  Email: <%= resultset.getString("Email") %><br>
	  Telefon: <%= resultset.getString("Phone") %><br>
	  Kraj: <%= resultset.getString("Country") %><br>
	  Miejscowosc: <%= resultset.getString("City") %><br>
	  
	  <%} %>
         
 <%
//**Should I input the codes here?**
        }
        catch(Exception e)
        {
             out.println("wrong entry"+e);
        }
%>   

<a href="EditUserDetails.jsp">Zmien swoje dane!</a> 
    
    
</body>
</html>