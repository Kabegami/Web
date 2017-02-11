package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

import BD.BDException;
import BD.Database;
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
}