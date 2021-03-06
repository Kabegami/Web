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
 
public class Login extends HttpServlet {
	public Login(){
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try{
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		JSONObject j = new JSONObject();
		j = Services.user.login(login,password);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(j);
		}
		catch(Exception e){
			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();
			out.println("erreur Servlet CreateUser");
			out.println(e);
		}
	}

}