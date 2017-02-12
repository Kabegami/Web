package BD;

import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class userTools {
	public static boolean userExists(String login) throws BDException{
		try{
			Connection c = Database.getMySQLConnection();
			Statement st = c.createStatement();
			boolean exists;
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
	
	public static Integer getID(String login) throws BDException{
		try{
			Integer id = -1;
			Connection c = Database.getMySQLConnection();
			Statement st = c.createStatement();
			String query = "SELECT id FROM users WHERE login = \'" + login +"\';";
			st.executeQuery(query);
			ResultSet rs = st.getResultSet();
			if (rs.next()){
				id = rs.getInt("id");
			}
			rs.close(); st.close(); c.close();
			return id;	
		}	
		catch(Exception e){
			throw new BDException("can't check id from user , exception = " + e);
		}
	}
	
	
	public static void addSession(Integer id, String s_key ) throws BDException{
		try {
			Connection c = Database.getMySQLConnection();
			String id_user = String.valueOf(id);
			//Sessions(s_key,id_user,temps_connection)
			String query = "INSERT INTO sessions VALUES(?,?,?);";
			PreparedStatement pst = c.prepareStatement(query);
			pst.setString(1, s_key);
			pst.setString(2, id_user);
			pst.executeUpdate();
			pst.close(); c.close();
			
		}
		catch(Exception e){
			throw new BDException("impossible d'ajouter l'utilisateur dans la table sessions , exception = " + e);
		}
		}
		
	}