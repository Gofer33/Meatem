package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Other.Group;
import announcements.Announcement;
import announcements.AnnouncementDAO;
import announcements.AnnouncementParser;

/**
 * Servlet implementation class Groups
 */
@WebServlet("/Groups")
public class Groups extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AnnouncementDAO announcementDAO;

	public Groups() {
		announcementDAO = new AnnouncementDAO();
	} 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		ArrayList<Group> groups = new ArrayList<Group>();
		groups = announcementDAO.loadGroups();
		out.print("<H1>Utworzone grupy:</H1>");
		
		for (int i = 0; i < groups.size(); i++) {
			
			out.print("----------------------------------------------------------------------<br \\>");
			out.print("[NAZWA] " + groups.get(i).getName() + "<br \\>");
			out.print("[CZLONKOW] " + groups.get(i).getUserNumber() + "<br \\>");
			out.print("<FORM ACTION=\"GroupView\" METHOD=\"POST\">");
			out.print("<input type=\"hidden\" name=\"name\" value=\"" + groups.get(i).getName() + "\" />");
			out.print("<INPUT TYPE=\"SUBMIT\" VALUE=\"Zobacz\">");
			out.print("</form>");
			
			out.print("<br \\>");
			
		}
		
		out.println("<a href=\"index.jsp\">wystawianie ogloszen</a>");
		
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
