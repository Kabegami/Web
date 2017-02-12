package BD;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

import sun.security.util.Password;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class sessionsTools {
	//Sessions(s_key,id_user,temps_connection)
	public static void addSession(Integer id, String s_key ) throws BDException{
		try {
			Connection c = Database.getMySQLConnection();
			String id_user = String.valueOf(id);
			String query = "INSERT INTO sessions VALUES (\"" + s_key + "\" ," + id_user + " , NOW())";
			Statement st = c.createStatement();
			st.executeUpdate(query);
			st.close(); c.close();
		}
		catch(Exception e){
			throw new BDException("impossible d'ajouter l'utilisateur dans la table sessions , exception = " + e);
		}
		}
	
	public static boolean sessionExist(Integer id) throws BDException{
		try{
		boolean exist;
		Connection c = Database.getMySQLConnection();
		String query = "SELECT * FROM sessions where id_user = ?;";
		PreparedStatement pst = c.prepareStatement(query);
		pst.setLong(1, id);
		ResultSet rs = pst.executeQuery();
		exist = rs.next();
		rs.close();pst.close();c.close();
		return exist;
		}
		catch(Exception e){
			throw new BDException("impossible de verifer si id dans la table sessions , exception = " + e);
		}
		}
	
	public static String genereClef() throws BDException{
		try {
			boolean keyExists;
			Connection c = Database.getMySQLConnection();
			PreparedStatement pst;
			String key = "";
			String query = "";
			do {
				UUID Vkey = UUID.randomUUID();
				key = Vkey.toString();
				System.out.print("key = "+key);
				query = "SELECT s_key FROM sessions where s_key = ?;";
				pst = c.prepareStatement(query);
				pst.setString(1,key);
				ResultSet rs = pst.executeQuery();
				keyExists = rs.next();
				rs.close();
				pst.close();
			} while(keyExists);
			c.close();
			return key;
			
		}
		catch(Exception e){
			throw new BDException("impossible de génere la clef, exception = " + e);
		}
	}
	public static void updateSession(Integer id) throws BDException{
		try{
		Connection c = Database.getMySQLConnection();
		String id_user = String.valueOf(id);
		String query = "UPDATE sessions SET temps_connection where id_user=?";
		PreparedStatement pst = c.prepareStatement(query);
		pst.setString(1, id_user);
		pst.executeUpdate();
		pst.close();c.close();
		}
		catch(Exception e){
			throw new BDException("impossible update la table sessions , exception = " + e);
		}
		}
}
