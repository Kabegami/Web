package Servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import BD.Database;
import BD.userTools;
 
public class CreateUser extends HttpServlet {
	public CreateUser(){
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try{
		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		/* on verifie si l'utilisateur existe */
		userTools.userExists(login);
		/* Ajoute utilisateur a la BD */
		Connection c = Database.getMySQLConnection();
		Statement st = c.createStatement();
		String q = "Insert into users values(null,\"+login+\",\"+password+\",\"+nom+prenom\"";
		st.executeUpdate(q);
		st.close();
		c.close();
		JSONObject j = Services.user.createUser(prenom,nom,login,password);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(j);
		}
		catch(Exception e){
			System.out.println("erreur Servlet CreateUser");
		}
	}

}
