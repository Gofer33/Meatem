package servlets;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import authorization.Validate;

import java.sql.*;



public class Register extends HttpServlet {
	public static final String SALT = "salthehe";
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        try{
        
        //loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver");

	//creating connection with the database 
          Connection  con=DriverManager.getConnection
                     ("jdbc:mysql://localhost/announcements?user=root&password=supersilnehaslo123");

        PreparedStatement ps=con.prepareStatement
                  ("insert into users(Login,Password) values(?,?)");
        String saltedPassword = pass + SALT;
        System.out.println("[PASS] = " + pass);
        System.out.println("[salted]" + saltedPassword);
        String hashedPassword = Validate.generateHash(saltedPassword);
        System.out.println("[hashed]" + hashedPassword);
        ps.setString(1, name);
        ps.setString(2, hashedPassword);
        System.out.println(hashedPassword);
        int i=ps.executeUpdate();
        
          if(i>0)
          {
            out.println("Zarejestrowano pomyslnie!");
          }
          out.println("<a href=\"home.jsp\">Strona glowna</a>");
        }
        catch(Exception se)
        {
            se.printStackTrace();
        }
	
      }
  }
