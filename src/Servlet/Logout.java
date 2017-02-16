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
 
public class Logout extends HttpServlet {
	public Logout(){
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try{
		String key = request.getParameter("key");
		JSONObject j = new JSONObject();
		j = Services.Logout.logout(key);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(j);
		}
		catch(Exception e){
			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();
			out.println("erreur Servlet Logout");
			out.println(e);
		}
	}

}