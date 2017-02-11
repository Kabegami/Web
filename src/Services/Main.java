package Services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import BD.BDException;

public class Main {

	public static void main(String[] args)   {
		try{
		user u = new user();
		JSONObject j = u.createUser("toto", "raoul", "lolol", "123");
		
		System.out.println(j);}
		catch(Exception e){
			System.out.println(e);
		}
	}
}


