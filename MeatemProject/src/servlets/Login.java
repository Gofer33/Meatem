package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import authorization.Validate;

import java.sql.*;

public class Login extends HttpServlet {
	
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        if(Validate.checkUser(login, pass))
        {
        	HttpSession session = request.getSession();
        	session.setAttribute("user", login);
			//setting session to expiry in 30 mins
        	Cookie userName = new Cookie("user", login);
        	userName.setMaxAge(1);
			session.setMaxInactiveInterval(30*60);
			response.addCookie(userName);
			response.sendRedirect("LoginSuccess.jsp");
        }
        else
        {
        	RequestDispatcher rd = getServletContext().getRequestDispatcher("/SignIn.jsp");
			PrintWriter outz= response.getWriter();
			outz.println("<font color=red>Nazwa uzytkownika lub haslo niepoprawne.</font>");
			rd.include(request, response);
        }
    }  
}