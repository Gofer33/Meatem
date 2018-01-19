<?xml version="1.0" encoding="UTF-8" ?>
<%@ page import="announcements.AnnouncementDAO" %>
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
    <H1>Strona Glowna</H1>
 	<a href="index.jsp">wystawianie ogloszen</a> <br>
 	<a href="SignUp.jsp">Rejestracja</a> <br>
 	<a href="SignIn.jsp">Logowanie</a> <br>
 	<a href="Announcements">Zobacz Ogloszenia</a> <br>
 	<a href="Groups">Przegladaj Grupy</a> <br>
 	<a href="CreateGroup.jsp">Zaloz Grupe!</a> <br>

 	
<%

String user = null;
if(session.getAttribute("user") == null){
	%><h3>Nie zalogowano</h3><%
}else {user = (String) session.getAttribute("user");
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user")) userName = cookie.getValue();
	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}

AnnouncementDAO dao = new AnnouncementDAO();

if(dao.isAdmin((String)session.getAttribute("user")))
{
	if(request.getParameter("backup") != null)
	{
		String dbName = "announcements";
		String dbUser = "root";
		String dbPass = "supersilnehaslo123";
		String executeCmd = "";
		executeCmd = "mysqldump " + dbName + " -u " + dbUser + " -p" + dbPass + " > E:/backup.sql";
		System.out.println(executeCmd);
		//Process runtimeProcess =Runtime.getRuntime().exec(executeCmd);
		Process runtimeProcess = Runtime.getRuntime().exec(new String[] { "bash", "-c", executeCmd });
		int processComplete = runtimeProcess.waitFor();
		if(processComplete == 0){
		System.out.println("Backup taken successfully");
		} else {
		System.out.println("Could not take mysql backup");
		}
	}
	if(request.getParameter("restorebackup") != null)
	{
		String dbName = "TESTDB";
		String dbUser = "root";
		String dbPass = "supersilnehaslo123";
		String com = "mysql -u " + dbUser+ " -p"+dbPass+" " + dbName+ " < E:/backup.sql";
		System.out.println(com);
		String[] executeCmd = {"bash", "-c", com };
		Process runtimeProcess =Runtime.getRuntime().exec(executeCmd);
		int processComplete = runtimeProcess.waitFor();
		if(processComplete == 0){
		System.out.println("success");
		} else {
		System.out.println("restore failure");
		}
	}
	%>
	<form action="home.jsp" method="post">
	<input type="submit" value="Zrob backup!" >
	<input type="hidden" name="backup" value="true" >
	</form>
	<form action="home.jsp" method="post">
	<input type="submit" value="Odzyskaj backup!" >
	<input type="hidden" name="restorebackup" value="true" >
	</form>
	<%
}




%>


<h3>Czesc <%=session.getAttribute("user") %></h3>
<a href="UserPanel.jsp">Moje konto</a> <br>
<form action="LogoutServlet" method="post">
<input type="submit" value="Wyloguj" >
</form>
<%
}%>


</body>
</html>