package announcements;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class AnnouncementParser {

	public static Announcement parseAnnouncementFromRequest(HttpServletRequest request) {
		Announcement an = new Announcement();
		String[] cats = request.getParameterValues("category");
		ArrayList<String> categories = new ArrayList<String>();
		if(cats != null)
		for(int i=0;i<cats.length;i++)
		{
			categories.add(cats[i]);
		}
		if(request.getParameter("city") != "")
			an.setCity(request.getParameter("city"));
		if(request.getParameter("country") != "")
			an.setCountry(request.getParameter("country"));
		if(request.getParameter("street") != "")
			an.setStreet(request.getParameter("street"));
		if(request.getParameter("people") != "")
			an.setUserLimit(Integer.parseInt(request.getParameter("people")));
		an.setCategories(categories);
		an.setText(request.getParameter("text1"));
		an.setTitle(request.getParameter("text2"));
		
		an.setAuthor(request.getParameter("userName"));
		
		return an;
	}

}
