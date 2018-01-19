package announcements;

import javax.servlet.http.HttpServletRequest;


public class SQLAnnouncementParser {

	public String createSaveQuery(Announcement ann) {
		String query = "";

		query = "INSERT INTO announcements (Title,Content,Author) VALUES ('" + ann.getTitle()
				+ "', '" + ann.getText() + "', '" + ann.getAuthor()+ "');";
		return query;
	}

	public String createLoadQuery() {
		String query = "";

		query = "SELECT * FROM announcements as a"
				+ " INNER JOIN announcement_details as ad ON ad.Announcement_ID = a.Announcement_ID;";
		return query;
	}
	
	public String announcementCategoriesQuerySave(int annID, int catID) {
		String query = "";

		query = "INSERT INTO announcement_categories (Announcement_ID,Category_ID) VALUES('" + annID + "', '" + catID + "');";
		return query;
	}
	
	public String getAnnouncementID(String title)
	{
		String query = "";
		
		query = "SELECT Announcement_ID FROM announcements WHERE Title = '" + title +"';";
		return query;
	}
	
	public String getUserID(String name)
	{
		String query = "";
		
		query = "SELECT User_ID FROM users WHERE Login = '" + name +"';";
		return query;
	}
	
	public String getIDOfCategory(String name)
	{
		String query = "";
		
		query = "SELECT Category_ID FROM categories WHERE Name = '" + name +"';";
		return query;
	}
	
	public String getCategories(int ID)
	{
		String query = "";
		
		query = "SELECT Name "
				+ "FROM announcement_categories as ac "
				+ "INNER JOIN categories AS c ON ac.Category_ID = c.Category_ID "
				+ "WHERE ac.Announcement_ID = " + ID + ";";
		return query;	
	}
	
	public String createEditUserDetailsQuery(HttpServletRequest request,int userID)
	{
		String query = "";
		
		query ="UPDATE user_details SET First_Name = '"
				+ request.getParameter("first_name") + "', Last_Name = '"
				+ request.getParameter("last_name") + "', Age = "
				+ request.getParameter("age") + ", Sex = '"
				+ request.getParameter("gender") + "', Email = '"
				+ request.getParameter("email") + "', Phone = "
				+ request.getParameter("phone") + ", Country = '"
				+ request.getParameter("country") + "', City = '"
				+ request.getParameter("city") + "' WHERE User_ID = " + userID + ";";
		return query;
	}
	
	public String createSaveDetailsQuery(Announcement ann, int annID) {
		String query = "";
		String city = "NULL";
		String country = "NULL";
		String street = "NULL";
		String date = "NULL";
		int userLimit = -5;
		int usersInterested = -10;
		
		if(ann.getCity() != null)
			city = ann.getCity();
		if(ann.getCountry() != null)
			country = ann.getCountry();
		if(ann.getStreet() != null)
			street = ann.getStreet();
		//if(ann.getDate() != null)
			//city = ann.getCity();
			userLimit = ann.getUserLimit();
			usersInterested = ann.getUserInterested();
		
			query ="UPDATE announcement_details SET Country = '"
					+ country + "', City = '"
					+ city + "', Street = '"
					+ street + "', User_Limit = "
					+ userLimit + ", Users_Interested = "
					+ usersInterested + " WHERE Announcement_ID = " + annID + ";";
			
		return query;
	}
	
	public String getAnnouncementByTitleQuery(String title)
	{
		String query = "";
		
		query = "SELECT * FROM announcements as a"
				+ " INNER JOIN announcement_details as ad ON ad.Announcement_ID = a.Announcement_ID"
				+ " WHERE a.Title = '" + title +"';";
		return query;
	}
	
	public String createAnnouncementsUsersQuery(int userID,int annID)
	{
		String query = "";
		
		query =  "INSERT INTO announcements_users (User_ID,Announcement_ID) VALUES('" + userID + "', '" + annID + "');";
		return query;
	}
	
	public String createCommentQuery(int userID, int annID, String content)
	{
		String query = "";
		
		query = "INSERT INTO comments(User_ID,Announcement_ID,Content) VALUES('" + userID + "', '" + annID + "', '" + content + "');";
		
		return query;
	}

	public String createLoadCommentsQuery(String title) {
		String query = "";
		
		query = "SELECT comments.Content, Date, users.Login FROM comments" +
				 " INNER JOIN announcements ON announcements.Announcement_ID = comments.Announcement_ID" +
				 " INNER JOIN users ON users.User_ID = comments.User_ID" +
				 " WHERE announcements.Title = '" + title +"';";
		
		return query;
	}

	public String createGroupQuery(String groupName) {
		String query = "";
		
		query = "INSERT INTO groups(Group_Name) VALUES('"+ groupName + "');";
		
		return query;
	}

	public String createGroupUserQuery(String groupName, int userID) {
		String query = "";
		
		query = "INSERT INTO group_users(Group_Name, User_ID) VALUES('"+ groupName + "', " + userID + ");";
		System.out.println(query);
		return query;
	}

	public String createLoadGroupsQuery() {
		String query = "";
		
		query = "SELECT g.Group_Name, g.Creation_Data, COUNT(gu.User_ID) as userNumber "
				+ "FROM groups as g "
				+ "INNER JOIN group_users as gu ON gu.Group_Name = g.Group_Name " 
				+ "GROUP BY g.Group_Name;";
		
		return query;
	}

	public String createGroupCommentQuery(int userID, String groupName,
			String content) {
		String query = "";
		
		query = "INSERT INTO group_comments(Group_Name,Author,Text) VALUES('" + groupName + "', '" + userID + "', '" + content + "');";
		
		return query;
	}

	public String createCheckUserInGroup(String groupName, int userID) {
		String query = "";
		
		query = "SELECT * FROM group_users WHERE Group_Name = '" + groupName + "' AND User_ID = " + userID + ";";
		
		return query;
	}

	public String createLoadGroupCommentsQuery(String groupName) {
		String query = "";
		
		query = "SELECT * FROM group_comments INNER JOIN users ON users.User_ID = group_comments.Author WHERE Group_Name = '" + groupName + "';";
		
		return query;
	}

	public String createCheckIfAdminQuery(int userID) {
		String query = "";
		
		query = "SELECT * FROM users WHERE User_ID = " + userID + " AND Role = 'admin';" ;
		
		return query;
	}

	public String createCheckIfUserOwnerOfAnn(int userID, int annID) {
		String query = "";
		
		query = "SELECT * FROM announcements_users WHERE User_ID = " + userID + " AND Announcement_ID = " + annID + ";";
		
		return query;
	}

	public String createRemoveAnnQuery(String title) {
		String query = "";
		
		query = "DELETE FROM announcements WHERE Title = '" + title + "';";
		
		return query;
	}

	public String createLoadWithFilterQuery(String filter) {
		String query = "";
		query = "SELECT DISTINCT Content,a.Announcement_ID,Title,Author,User_Limit,Users_Interested FROM announcements as a"
				+ " INNER JOIN announcement_details as ad ON ad.Announcement_ID = a.Announcement_ID"
				+ " INNER JOIN announcement_categories as ac ON ac.Announcement_ID = a.Announcement_ID"
				+ " INNER JOIN categories as c ON c.Category_ID = ac.Category_ID"
				+ " WHERE a.Title LIKE '%" + filter + "%'" 
				+ " OR Content LIKE '%" + filter + "%'"
				+ " OR c.Name LIKE '%" + filter + "%'"
				+ " OR Country LIKE '%" + filter + "%'"
				+ " OR City LIKE '%" + filter + "%'"
				+ " OR Street LIKE '%" + filter + "%';";
		return query;
	}
}
