<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title>Logowanie</title>
    </head>
    <body>
        <form method="post" action="login">
        Nazwa uzytkownika:<input type="text" name="login" /><br/>
        Haslo:<input type="password" name="pass" /><br/>
        <input type="submit" value="Zaloguj" />
        </form>
        <a href="home.jsp">Strona glowna</a>
    </body>
</html>