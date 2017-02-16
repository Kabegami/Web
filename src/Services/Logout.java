package Services;

import org.json.JSONException;
import org.json.JSONObject;

import BD.BDException;
import BD.sessionsTools;

public class Logout {
	public static JSONObject logout(String key) throws BDException, JSONException{
		JSONObject j = new JSONObject();
		if(key == null){
			j.put("state", "error");
			j.put("error", "key is null");
			return j;
		}
		// probleme le session tools nous indique que l'utilisateur n'existe pas alors qu'il existe TODO
		int id = sessionsTools.getId(key);
		//l'utilisateur n'existe pas
		if(id == -1){
			j.put("state", "error");
			j.put("error", "user/key don't exist");
			return j;
		}
		sessionsTools.removeSession(id);
		j.put("state", "ok");
		j.put("sessions supprimer", true);
		return j;
	}
}
