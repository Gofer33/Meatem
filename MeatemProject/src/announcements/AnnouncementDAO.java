package announcements;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import Other.Comment;
import Other.Group;

import com.mysql.jdbc.ResultSetMetaData;

public class AnnouncementDAO {

	private final static String DBURL = "jdbc:mysql://localhost/announcements";
	private final static String DBUSER = "root";
	private final static String DBPASS = "";
	private final static String DBDRIVER = "com.mysql.jdbc.Driver";

	private java.sql.Connection connection;
	private java.sql.Connection connection2;

	private java.sql.Statement statement;
	private java.sql.Statement statement2;

	private String query;

	private SQLAnnouncementParser sqlAnnouncementParser;

	public AnnouncementDAO() {

		sqlAnnouncementParser = new SQLAnnouncementParser();
	}

	public void save(Announcement ann) {
		query = sqlAnnouncementParser.createSaveQuery(ann);
		int annID = -1;
		int catID = -1;

		try {
			Class.forName(DBDRIVER).newInstance();
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			statement = connection.createStatement();
			//add announcement to announcements table
			statement.executeUpdate(query);
			//get ID of that announcement
			//TODO: ZMIENIC NA TITLE
			query = sqlAnnouncementParser.getAnnouncementID(ann.getTitle());
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				annID = rs.getInt("Announcement_ID");
			}
			
			ArrayList<String> categories = ann.getCategories();
			for(int i = 0;i<categories.size();i++)
			{
				//get ID of category
				query = sqlAnnouncementParser.getIDOfCategory(categories.get(i));
				rs = statement.executeQuery(query);
				while (rs.next()) {
					catID = rs.getInt("Category_ID");
				}
				
				//add record to announcement categories table
				query = sqlAnnouncementParser.announcementCategoriesQuerySave(annID,catID);
				statement.executeUpdate(query);
			}
			query = sqlAnnouncementParser.createSaveDetailsQuery(ann,annID);
			statement.executeUpdate(query);
			query = sqlAnnouncementParser.getUserID(ann.getAuthor());
			int userID = 0;
			rs = statement.executeQuery(query);
			while (rs.next()) {
				userID = rs.getInt("User_ID");
			}
			query = sqlAnnouncementParser.createAnnouncementsUsersQuery(userID,annID);
			statement.executeUpdate(query);
			statement.close();
			connection.close();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}
	
	public void addInterestedUser(String name, String title)
	{
		int userID = -1;
		int annID = -1;
		
		query = sqlAnnouncementParser.getUserID(name);
		
		try {
			Class.forName(DBDRIVER).newInstance();
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				userID = rs.getInt("User_ID");
			}
			System.out.println("TITLE = " + title);
			query = sqlAnnouncementParser.getAnnouncementID(title);
			rs = statement.executeQuery(query);
			while (rs.next()) {
				annID = rs.getInt("Announcement_ID");
			}
			
			query = sqlAnnouncementParser.createAnnouncementsUsersQuery(userID,annID);
			statement.executeUpdate(query);
			
			
			statement.close();
			connection.close();
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}
	
	public void editUserDetails(HttpServletRequest request)
	{
		query = sqlAnnouncementParser.getUserID(request.getParameter("userName"));
		int userID = 0;
		
		try {
			Class.forName(DBDRIVER).newInstance();
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				userID = rs.getInt("User_ID");
			}
			query = sqlAnnouncementParser.createEditUserDetailsQuery(request,userID);
			statement.executeUpdate(query);
			statement.close();
			connection.close();
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		
	}

	public ArrayList<Announcement> load() {
		query = sqlAnnouncementParser.createLoadQuery();
		ArrayList<Announcement> anns = new ArrayList<Announcement>();
		try {
			Class.forName(DBDRIVER).newInstance();
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			connection2 = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			statement = connection.createStatement();
			statement2 = connection2.createStatement();
			ResultSet rs = statement.executeQuery(query);
			ResultSet rs2;
			int counter = 0;
			
			while (rs.next()) {
				ArrayList<String> cats = new ArrayList<String>();
				Announcement ann = new Announcement();
				ann.setText(rs.getString("Content"));
				ann.setId(rs.getInt("Announcement_ID"));
				ann.setTitle(rs.getString("Title"));
				ann.setAuthor(rs.getString("Author"));
				ann.setUserLimit(rs.getInt("User_Limit"));
				ann.setUserInterested(rs.getInt("Users_Interested"));
				
				
				query = sqlAnnouncementParser.getCategories(ann.getId());
				rs2 = statement2.executeQuery(query);
				counter = 0;
				while (rs2.next()) {
					cats.add(rs2.getString("Name"));
					counter ++;
				}
				ann.setCategories(cats);
				anns.add(ann);
			}

			statement.close();
			connection.close();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		return anns;
	}
	
	public void addComment(String userName, String title, String content)
	{
		int userID = -1;
		int annID = -1;
		
		query = sqlAnnouncementParser.getUserID(userName);
		
		try {
			Class.forName(DBDRIVER).newInstance();
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				userID = rs.getInt("User_ID");
			}
			query = sqlAnnouncementParser.getAnnouncementID(title);
			rs = statement.executeQuery(query);
			while (rs.next()) {
				annID = rs.getInt("Announcement_ID");
			}
			
			query = sqlAnnouncementParser.createCommentQuery(userID,annID, content);
			statement.executeUpdate(query);
			
			
			statement.close();
			connection.close();
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}
	
	public Announcement getAnnouncementByTitle (String title)
	{
		query = sqlAnnouncementParser.getAnnouncementByTitleQuery(title);
		Announcement ann = new Announcement();
		try {
			Class.forName(DBDRIVER).newInstance();
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			connection2 = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			statement = connection.createStatement();
			statement2 = connection2.createStatement();
			ResultSet rs = statement.executeQuery(query);
			ResultSet rs2;
			int counter = 0;
			
			while (rs.next()) {
				ArrayList<String> cats = new ArrayList<String>();
				ann.setText(rs.getString("Content"));
				ann.setId(rs.getInt("Announcement_ID"));
				ann.setTitle(rs.getString("Title"));
				ann.setAuthor(rs.getString("Author"));
				ann.setCity(rs.getString("City"));
				ann.setCountry(rs.getString("Country"));
				ann.setStreet(rs.getString("Street"));
				ann.setUserLimit(rs.getInt("User_Limit"));
				ann.setUserInterested(rs.getInt("Users_Interested"));
				
				query = sqlAnnouncementParser.getCategories(ann.getId());
				rs2 = statement2.executeQuery(query);
				counter = 0;
				while (rs2.next()) {
					cats.add(rs2.getString("Name"));
					counter ++;
				}
				ann.setCategories(cats);
			}

			statement.close();
			connection.close();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		return ann;
	}

	public ArrayList<Comment> loadComments(String title) {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		
		query = sqlAnnouncementParser.createLoadCommentsQuery(title);
		try {
			Class.forName(DBDRIVER).newInstance();
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				Comment com = new Comment();
				com.setContent(rs.getString("Content"));
				//com.setDate(rs.getString("Date"));
				com.setAuthor(rs.getString("users.Login"));				

				comments.add(com);
			}

			statement.close();
			connection.close();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		
		return comments;
	}

	public void createGroup(String userName, String groupName) {
		int userID = -1;
		query = sqlAnnouncementParser.createGroupQuery(groupName);
		
		try {
			Class.forName(DBDRIVER).newInstance();
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			statement = connection.createStatement();
			statement.executeUpdate(query);
			query = sqlAnnouncementParser.getUserID(userName);
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				userID = rs.getInt("User_ID");
			}
			
			query = sqlAnnouncementParser.createGroupUserQuery(groupName, userID);
			statement.executeUpdate(query);
			
			statement.close();
			connection.close();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

	public ArrayList<Group> loadGroups() {
ArrayList<Group> groups = new ArrayList<Group>();
		
		query = sqlAnnouncementParser.createLoadGroupsQuery();
		try {
			Class.forName(DBDRIVER).newInstance();
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				Group g = new Group();
				g.setName(rs.getString("Group_Name"));
				g.setUserNumber(rs.getInt("userNumber"));			
				groups.add(g);
			}

			statement.close();
			connection.close();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		
		return groups;
	}

	public void addGroupComment(String groupName, String userName,
			String content) {
		int userID = -1;
		
		query = sqlAnnouncementParser.getUserID(userName);
		
		try {
			Class.forName(DBDRIVER).newInstance();
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				userID = rs.getInt("User_ID");
			}
			
			query = sqlAnnouncementParser.createGroupCommentQuery(userID, groupName, content);
			statement.executeUpdate(query);
			
			
			statement.close();
			connection.close();
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		
	}

	public void addToGroup(String groupName, String userName) {
		int userID = -1;
		
		query = sqlAnnouncementParser.getUserID(userName);
		
		try {
			Class.forName(DBDRIVER).newInstance();
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				userID = rs.getInt("User_ID");
			}
			
			query = sqlAnnouncementParser.createGroupUserQuery(groupName, userID);
			statement.executeUpdate(query);
			
			
			statement.close();
			connection.close();
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		
	}

	public boolean isInGroup(String groupName, String userName) throws SQLException {
int userID = -1;
		
		query = sqlAnnouncementParser.getUserID(userName);
		ResultSet rs = null;
		int i = 0;
		try {
			Class.forName(DBDRIVER).newInstance();
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			
			while (rs.next()) {
				userID = rs.getInt("User_ID");
			}
			
			query = sqlAnnouncementParser.createCheckUserInGroup(groupName, userID);
			rs = statement.executeQuery(query);
			while (rs.next()) {
				i++;
			}
			statement.close();
			connection.close();
				
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		
		
		System.out.println("[TEST]" + i);
		if(i == 0)
			return false;
		else return true;
	}

	public ArrayList<Comment> loadGroupComments(String groupName) {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		
		query = sqlAnnouncementParser.createLoadGroupCommentsQuery(groupName);
		try {
			Class.forName(DBDRIVER).newInstance();
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				Comment com = new Comment();
				com.setContent(rs.getString("Text"));
				//com.setDate(rs.getString("Date"));
				com.setAuthor(rs.getString("users.Login"));				

				comments.add(com);
			}

			statement.close();
			connection.close();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		
		return comments;
	}

	public boolean isAdmin(String userName) {
		query = sqlAnnouncementParser.getUserID(userName);
		int userID = -1;
		ResultSet rs = null;
		int i = 0;
		try {
			Class.forName(DBDRIVER).newInstance();
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			
			while (rs.next()) {
				userID = rs.getInt("User_ID");
			}
			
			query = sqlAnnouncementParser.createCheckIfAdminQuery(userID);
			rs = statement.executeQuery(query);
			while (rs.next()) {
				i++;
			}
			statement.close();
			connection.close();
				
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		
		
		System.out.println("[TEST]" + i);
		if(i == 0)
			return false;
		else return true;
	}

	public boolean isUserOwnerOfAnn(String title, String userName) {
		query = sqlAnnouncementParser.getUserID(userName);
		int userID = -1;
		int annID = -1;
		ResultSet rs = null;
		int i = 0;
		try {
			Class.forName(DBDRIVER).newInstance();
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			
			while (rs.next()) {
				userID = rs.getInt("User_ID");
			}
			
			query = sqlAnnouncementParser.getAnnouncementID(title);
			rs = statement.executeQuery(query);
			while (rs.next()) {
				annID = rs.getInt("Announcement_ID");
			}
			
			query = sqlAnnouncementParser.createCheckIfUserOwnerOfAnn(userID, annID);
			rs = statement.executeQuery(query);
			while (rs.next()) {
				i++;
			}
			statement.close();
			connection.close();
				
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		
		
		System.out.println("[TEST]" + i);
		if(i == 0)
			return false;
		else return true;
	}

	public void removeAnn(String title) {
		try {
			Class.forName(DBDRIVER).newInstance();
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			statement = connection.createStatement();
			
			
			query = sqlAnnouncementParser.createRemoveAnnQuery(title);
			statement.executeUpdate(query);
			
			
			statement.close();
			connection.close();
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

	public ArrayList<Announcement> loadWithFilter(String filter) {
		query = sqlAnnouncementParser.createLoadWithFilterQuery(filter);
		ArrayList<Announcement> anns = new ArrayList<Announcement>();
		try {
			Class.forName(DBDRIVER).newInstance();
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			connection2 = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			statement = connection.createStatement();
			statement2 = connection2.createStatement();
			ResultSet rs = statement.executeQuery(query);
			ResultSet rs2;
			int counter = 0;
			
			while (rs.next()) {
				ArrayList<String> cats = new ArrayList<String>();
				Announcement ann = new Announcement();
				ann.setText(rs.getString("Content"));
				ann.setId(rs.getInt("Announcement_ID"));
				ann.setTitle(rs.getString("Title"));
				ann.setAuthor(rs.getString("Author"));
				ann.setUserLimit(rs.getInt("User_Limit"));
				ann.setUserInterested(rs.getInt("Users_Interested"));
				
				
				query = sqlAnnouncementParser.getCategories(ann.getId());
				rs2 = statement2.executeQuery(query);
				counter = 0;
				while (rs2.next()) {
					cats.add(rs2.getString("Name"));
					counter ++;
				}
				ann.setCategories(cats);
				anns.add(ann);
			}

			statement.close();
			connection.close();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		return anns;
	}
}
