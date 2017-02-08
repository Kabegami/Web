package Servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
 
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
		JSONObject j = Services.user.createUser(prenom,nom,login,password);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(j);
		}
		catch(Exception e){}
	}

}
