package Services;

import org.json.JSONException;
import org.json.JSONObject;

public class main {

	public static void main(String[] args) throws JSONException {
		user u = new user();
		JSONObject j = u.createUser("toto", "raoul", "lolol", "123");
		System.out.println(j);
	}

}
