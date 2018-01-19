package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import announcements.Announcement;
import announcements.AnnouncementDAO;
import announcements.AnnouncementParser;

public class CreateAnnouncement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AnnouncementParser announcementParser;
	private AnnouncementDAO announcementDAO;

	public CreateAnnouncement() {
		announcementParser = new AnnouncementParser();
		announcementDAO = new AnnouncementDAO();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("Nie wchodzic!");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<body>");
				Announcement ann = announcementParser
						.parseAnnouncementFromRequest(request);
				announcementDAO.save(ann);
				out.println("<a href=\"index.jsp\">wystawianie ogloszen</a>");
				out.println("</body>");
				out.println("</html>");
				response.sendRedirect("http://localhost:8080/MeatemProject/Announcements");
}}