package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import announcements.AnnouncementDAO;
import announcements.AnnouncementParser;

/**
 * Servlet implementation class CreateGroup
 */
@WebServlet("/CreateGroup")
public class CreateGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AnnouncementDAO announcementDAO;

	public CreateGroup() {
		announcementDAO = new AnnouncementDAO();
	} 


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		
		announcementDAO.createGroup(request.getParameter("userName"), request.getParameter("name"));
		response.sendRedirect("http://localhost:8080/MeatemProject/home.jsp");
		out.println("</body>");
		out.println("</html>");
	}

}
