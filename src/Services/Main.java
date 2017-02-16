package Services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import BD.BDException;

public class Main {

	public static void main(String[] args)   {
		try{
		user u = new user();
		Logout l = new Logout();
		//JSONObject j = u.createUser("toto", "raoul", "lolol", "123");
		JSONObject j = u.login("lolol","123");
		System.out.println(j);
		JSONObject j2 = l.logout("123");
		System.out.println(j2);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}


