package Services;

import java.sql.Connection;
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
			/* Ajoute utilisateur a la BD */
			Connection c = Database.getMySQLConnection();
			Statement st = c.createStatement();
			String q = "Insert into users values(null,"+login+","+password+","+nom+","+prenom+";";
			st.executeUpdate(q);
			st.close();
			c.close();
		
			JSONObject j = new JSONObject();
			j.put("state","ok");
			j.put("key","clef generer");
			return j;
	}
		else {
			JSONObject j = new JSONObject();
			j.put("state","problem");
			return j;
		}
		}
}