package Services;

import org.json.JSONException;
import org.json.JSONObject;

public class user {
	public static JSONObject createUser(String prenom, String nom, String login, String password) throws JSONException{
		JSONObject j = new JSONObject();
		j.put("state","ok");
		j.put("key","clef generer");
		return j;
	}
}