package authorization;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class Validate
 {
	public static final String SALT = "salthehe";
     public static boolean checkUser(String login,String pass) 
     {
      boolean st =false;
      try{

	 //loading drivers for mysql
         Class.forName("com.mysql.jdbc.Driver");

 	 //creating connection with the database 
         Connection con=DriverManager.getConnection
                        ("jdbc:mysql://localhost/announcements?user=root&password=supersilnehaslo123");
         PreparedStatement ps =con.prepareStatement
                             ("select * from users where Login=? and Password=?");
         String saltedPassword = pass + SALT;
         System.out.println("[salted]" + saltedPassword);
         String hashedPassword = generateHash(saltedPassword);
         System.out.println("[hashed]" + hashedPassword);
         ps.setString(1, login);
         ps.setString(2, hashedPassword);
         ResultSet rs =ps.executeQuery();
         st = rs.next();
        
      }catch(Exception e)
      {
          e.printStackTrace();
      }
         return st;                 
  }  
     public static String generateHash(String input) {
 		StringBuilder hash = new StringBuilder();

 		try {
 			MessageDigest sha = MessageDigest.getInstance("SHA-1");
 			byte[] hashedBytes = sha.digest(input.getBytes());
 			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
 					'a', 'b', 'c', 'd', 'e', 'f' };
 			for (int idx = 0; idx < hashedBytes.length; ++idx) {
 				byte b = hashedBytes[idx];
 				hash.append(digits[(b & 0xf0) >> 4]);
 				hash.append(digits[b & 0x0f]);
 			}
 		} catch (NoSuchAlgorithmException e) {
 			// handle error here.
 		}

 		return hash.toString();
 	}
}