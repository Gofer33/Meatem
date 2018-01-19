package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Other.Comment;
import announcements.AnnouncementDAO;

/**
 * Servlet implementation class GroupView
 */
@WebServlet("/GroupView")
public class GroupView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AnnouncementDAO announcementDAO;

	public GroupView() {
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
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		
		out.println("WITAJ W " + request.getParameter("name"));
		out.println("<br \\>");
		out.println("<br \\>");
		out.println("<br \\>");
		
		try {
			if(announcementDAO.isInGroup(request.getParameter("name"),(String)session.getAttribute("user")))
			{
				System.out.println("JEST W GRUPIE");
			if(request.getParameter("comment") != null)
				announcementDAO.addGroupComment(request.getParameter("name"), request.getParameter("userName"), request.getParameter("text2"));
			
			displayComments((String)request.getParameter("name"), out);
			
			out.println("DODAJ KOMENTARZ");
			out.println("<form action=\"GroupView\" method=\"post\">");
			out.println("<TEXTAREA NAME=\"text2\"ROWS=\"5\"></TEXTAREA>");
			out.println("<br \\>");
			out.println("<input type=\"submit\" value=\"Wyslij\" >");
			out.println("<input type=\"hidden\" name=\"name\" value=\""+request.getParameter("name")+"\" >");
			out.println("<input type=\"hidden\" name=\"comment\" value=\"true\" >");
			out.println("<input type=\"hidden\" name=\"userName\" value=\""+session.getAttribute("user")+"\" >");
			out.println("</form><%}%>");
			}
			else
			{
				if(request.getParameter("join") != null)
					announcementDAO.addToGroup(request.getParameter("name"),(String)session.getAttribute("user"));
				
				out.println("<form action=\"GroupView\" method=\"post\">");
				out.println("<br \\>");
				out.println("<input type=\"submit\" value=\"Dolacz\" >");
				out.println("<input type=\"hidden\" name=\"name\" value=\""+request.getParameter("name")+"\" >");
				out.println("<input type=\"hidden\" name=\"userName\" value=\""+session.getAttribute("user")+"\" >");
				out.println("<input type=\"hidden\" name=\"join\" value=\"true\" >");
				out.println("</form><%}%>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println("</body>");
		out.println("</html>");
	}

	private void displayComments(String groupName, PrintWriter out) {
		ArrayList<Comment> comments = announcementDAO.loadGroupComments(groupName);
		
		for( int i=0; i<comments.size(); i++)
		{
			out.println("----------------------------------------------------------<br \\>");
			out.println(comments.get(i).getAuthor() + "<br \\>");
			out.println(comments.get(i).getContent() + "<br \\>");
		}
		
	}

}
