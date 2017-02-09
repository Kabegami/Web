package BD;

import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.Connection;


public class userTools {
	public static boolean userExists(String login) throws BDException{
		try{
			boolean isUser;
			java.sql.Connection c = Database.getMySQLConnection();
			String query = "Select * from login where login = "+login+";";
			Statement st = c.createStatement();
			ResultSet ns = st.executeQuery(query);
			if (ns.next()){
				isUser = true;
			}
			else{
				isUser = false;
				}
			ns.close();
			st.close();
			c.close();
			return isUser;		
		}	
		catch(Exception e){
			throw new BDException("can't check user");
		}
	}
}