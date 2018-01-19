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
    <H1>Wystawianie ogłoszeń!</H1>
  <%
    try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = 
				DriverManager.getConnection
            ("jdbc:mysql://localhost/announcements?user=root&password=supersilnehaslo123");

		Statement statement = connection.createStatement() ;

       resultset =statement.executeQuery("select * from categories") ;
%>


    Kategoria:
    <br>
    <FORM ACTION="sub1" METHOD="POST">
        <%  while(resultset.next()){ %>
        <input type="checkbox" name="category" value="<%= resultset.getString(2)%>" /> <%= resultset.getString(2)%> <br>
        <% } %>
        <input type="hidden" name="userName" value="<%=session.getAttribute("user") %>" />
		
<%
//**Should I input the codes here?**
        }
        catch(Exception e)
        {
             out.println("wrong entry"+e);
        }
%>
    
    <BR>
    	Wpisz tytul swojego ogloszenia: <BR> <TEXTAREA NAME="text2"
                ROWS="1"></TEXTAREA> <BR>
        Wpisz treść swojego ogłoszenia: <BR> <TEXTAREA NAME="text1"
                ROWS="5"></TEXTAREA>
        <BR>       
        Ile osob szukasz: <BR>	  <input type="text" NAME="people"ROWS="1"> <BR>
        <BR>        
        Kraj: <BR>	  <input type="text" NAME="country"ROWS="1"> <BR>
        Miasto: <BR>	  <input type="text" NAME="city"ROWS="1"> <BR>
        Ulica: <BR>	  <input type="text" NAME="street"ROWS="1"> <BR>
        Data spotkania: <BR>	  <input type="text" NAME="date"ROWS="1"> <BR>
        
         <BR> <INPUT TYPE="SUBMIT" VALUE="Wyślij">
    </FORM>
</body>
</html>