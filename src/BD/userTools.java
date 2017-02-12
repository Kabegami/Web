package BD;

import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;


public class userTools {
	public static boolean userExists(String login) throws BDException{
		try{
			Connection c = Database.getMySQLConnection();
			Statement st = c.createStatement();
			boolean exists;
			/* Fait une erreur de syntaxe SQL alors que cela marche dans php my admin WTF */
			String query = "SELECT * FROM users WHERE login = \'" + login +"\';";
			st.executeQuery(query);
			System.out.println(query);
			ResultSet rs = st.getResultSet();
				
			// vérifie s'il y a une ligne dans le résultat
			exists = rs.next();
			rs.close(); st.close(); c.close();
			return exists;	
		}	
		catch(Exception e){
			throw new BDException("can't check user , exception = " + e);
		}
	}
}