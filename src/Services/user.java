package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

import BD.BDException;
import BD.Database;
import BD.sessionsTools;
import BD.userTools;

public class user {
	
	public static JSONObject createUser(String prenom, String nom, String login, String password) throws JSONException, BDException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		/* on verifie si l'utilisateur existe */
		if (!userTools.userExists(login)){
			Connection c = Database.getMySQLConnection();
			/*la premier valeur est a null car l'id s'incremente tout seul 
			 * schema de la table : (id,login,password,prenom,nom*/
			String query = "INSERT INTO users VALUES (null, ?, ?, ?, ?);";
			PreparedStatement pst = c.prepareStatement(query);
			pst.setString(1, login);
			pst.setString(2,password );
			pst.setString(3, prenom);
			pst.setString(4, nom);
			
			pst.executeUpdate();
			pst.close(); c.close();
		
			JSONObject j = new JSONObject();
			j.put("state","ok");
			j.put("key","clef generer");
			return j;
	}
		else {
			JSONObject j = new JSONObject();
			j.put("state","problem");
			j.put("erreur", "l'utilisateur existe deja");
			return j;
		}
		}
	public static JSONObject login(String login, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException, BDException{
		boolean exists;
		Connection c = Database.getMySQLConnection();
		String query = "SELECT id from users where password = ? and login = ?;";
		PreparedStatement pst = c.prepareStatement(query);
		pst.setString(1, password);
		pst.setString(2, login);
		pst.executeQuery();
		ResultSet rs = pst.getResultSet();
		
		// vérifie s'il y a une ligne dans le résultat
		exists = rs.next();
		rs.close(); pst.close(); c.close();
		JSONObject j = new JSONObject();
		Integer id = userTools.getID(login);
		if (exists) {
			String key = sessionsTools.genereClef();
			//on ajoute l'utilisateur dans la table session
			if (!sessionsTools.sessionExist(id)){
				sessionsTools.addSession(id,key);
				j.put("state", "ok");
				j.put("id",id);
				j.put("login", login);
				j.put("key",key);
			}
			else{
				sessionsTools.updateSession(id);
				j.put("state", "ok");
				j.put("session deja existante", "temps_connection mis a jour");
				
			}
			
		}
		else {
			j.put("state", "error");
			j.put("probleme ","login ou mdp non valide");
		}
		return j;
	}
}