package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import announcements.Announcement;
import announcements.AnnouncementDAO;
import announcements.AnnouncementParser;

/**
 * Servlet implementation class Announcements
 */
@WebServlet("/Announcements")
public class Announcements extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AnnouncementParser announcementParser;
	private AnnouncementDAO announcementDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Announcements() {
    	announcementParser = new AnnouncementParser();
    	announcementDAO = new AnnouncementDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		ArrayList<Announcement> announcements = new ArrayList<Announcement>();
		
		out.print("<H1>Ogloszenia w bazie:</H1>");
		if(request.getParameter("deleteann") != null)
		{
			announcementDAO.removeAnn(request.getParameter("title"));
		}
		
		out.print("<FORM ACTION=\"Announcements\" METHOD=\"GET\">");
		out.print("<input type=\"text\" NAME=\"search\"ROWS=\"1\">");
		out.print("<input type=\"hidden\" NAME=\"searched\"value=\"TRUE\">");
		out.print("<INPUT TYPE=\"SUBMIT\" VALUE=\"Szukaj\"> <br \\>");
		out.print("</form>");
		
		if(request.getParameter("searched") == null)
		{
			announcements = announcementDAO.load();
		}
		else
		{
			announcements = announcementDAO.loadWithFilter(request.getParameter("search"));
		}
		ArrayList<String> categories = new ArrayList<String>();
		for (int i = 0; i < announcements.size(); i++) {
			
			out.print("----------------------------------------------------------------------<br \\>");
			out.print("[TYTUL] " + announcements.get(i).getTitle() + "<br \\>");
			
			out.print("[AUTOR] " + announcements.get(i).getAuthor() + "<br \\>");
			out.print("[TEKST] " + announcements.get(i).getText() + "<br \\>");
			out.print("[KATEGORIE] ");
			categories = announcements.get(i).getCategories();
			for(int j = 0; j<categories.size();j++)
			{
				out.print(" [" + categories.get(j) +"]");
			}
			out.print("<br \\>");
			out.print("[MIEJSCE] " + announcements.get(i).getUserInterested() + "\\" + announcements.get(i).getUserLimit() + "<br \\>");
			
			out.print("<FORM ACTION=\"AnnouncementView\" METHOD=\"POST\">");
			out.print("<input type=\"hidden\" name=\"title\" value=\""+announcements.get(i).getTitle()+"\" />");
			out.print("<INPUT TYPE=\"SUBMIT\" VALUE=\"Zobacz\">");
			out.print("</form>");
			
			if(announcementDAO.isAdmin((String)session.getAttribute("user")) || announcementDAO.isUserOwnerOfAnn(announcements.get(i).getTitle(), (String)session.getAttribute("user")))
			{
				out.print("<FORM ACTION=\"Announcements\" METHOD=\"GET\">");
				out.print("<input type=\"hidden\" name=\"title\" value=\""+announcements.get(i).getTitle()+"\" />");
				out.print("<input type=\"hidden\" name=\"userName\" value=\""+(String)session.getAttribute("user")+"\" />");
				out.print("<input type=\"hidden\" name=\"deleteann\" value=\"true\" />");
				out.print("<INPUT TYPE=\"SUBMIT\" VALUE=\"Usun\">");
				out.print("</form>");
			}
		}
	
		out.print("<br \\>");
		out.println("<a href=\"index.jsp\">wystawianie ogloszen</a>");
		out.print("<br \\>");
		out.println("<a href=\"home.jsp\">strona glowna</a>");
		out.print("<br \\>");
		out.println("<a href=\"Announcements\">wszystkie ogloszenia</a>");
		
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
