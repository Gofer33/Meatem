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

import Other.Comment;
import announcements.Announcement;
import announcements.AnnouncementDAO;
import announcements.AnnouncementParser;

@WebServlet("/AnnouncementView")
public class AnnouncementView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AnnouncementDAO announcementDAO;

    public AnnouncementView() {
		announcementDAO = new AnnouncementDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Join") != null)
		{
			announcementDAO.addInterestedUser(request.getParameter("Name"),request.getParameter("title"));
		}
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		Announcement ann = announcementDAO.getAnnouncementByTitle(request.getParameter("title"));
		out.println("<html>");
		out.println("<body>");
		out.println(ann.getTitle() + "<br \\>");
		out.println(ann.getText() + "<br \\>");
		out.println(ann.getAuthor() + "<br \\>");
		out.println(ann.getCity() + "<br \\>");
		out.println(ann.getCountry() + "<br \\>");
		out.println(ann.getStreet() + "<br \\>");
		out.println(ann.getUserInterested() + "\\");
		out.println(ann.getUserLimit() + "<br \\>");
		if(ann.getUserInterested() < ann.getUserLimit())
		{
			out.println("<form action=\"AnnouncementView\" method=\"post\">");
			out.println("<input type=\"submit\" value=\"Dolacz\" >");
			out.println("<input type=\"hidden\" name=\"title\" value=\""+ann.getTitle()+"\" >");
			out.println("<input type=\"hidden\" name=\"Name\" value=\""+session.getAttribute("user")+"\" >");
			out.println("<input type=\"hidden\" name=\"Join\" value=\"true\" >");
			out.println("</form><%}%>");
		}
		out.println("<a href=\"index.jsp\">wystawianie ogloszen</a>");
		out.println("<a href=\"home.jsp\">strona glowna</a><br \\>");
		
		
		
		if(request.getParameter("text2") != null)
			announcementDAO.addComment(request.getParameter("Name"),request.getParameter("title"),request.getParameter("text2"));
		displayComments(ann.getTitle(), out);
		out.println("<form action=\"AnnouncementView\" method=\"post\">");
		out.println("<TEXTAREA NAME=\"text2\"ROWS=\"5\"></TEXTAREA>");
		out.println("<input type=\"submit\" value=\"Wyslij\" >");
		out.println("<input type=\"hidden\" name=\"title\" value=\""+ann.getTitle()+"\" >");
		out.println("<input type=\"hidden\" name=\"Name\" value=\""+session.getAttribute("user")+"\" >");
		out.println("</form><%}%>");
		
		out.println("</body>");
		out.println("</html>");
	}
	
	private void displayComments(String title, PrintWriter out)
	{
		ArrayList<Comment> comments = announcementDAO.loadComments(title);
		
		for( int i=0; i<comments.size(); i++)
		{
			out.println("----------------------------------------------------------<br \\>");
			out.println(comments.get(i).getAuthor() + "<br \\>");
			out.println(comments.get(i).getContent() + "<br \\>");
		}
	}
}
