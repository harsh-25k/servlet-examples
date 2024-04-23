package com.servlet;
 
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.event.DAO_Impl;
import com.event.DatabaseConnection;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/compare")
 
public class CompareServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.setContentType("text/html");
 
		DAO_Impl dbConnection = new DAO_Impl();
 
		Integer compareCount = 0;
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		try {
			compareCount = getCompareCount(dbConnection.getConn());
			
			response.setContentType("application/json");
			String json = gson.toJson(compareCount);
			response.getWriter().write(json);
//			out.println("The ids with comparison are: " + compareCount);
		} catch (Exception e) {
//			out.println("<h1>Hello, in the catch Servlet!</h1>");
			out.println(e.getMessage());
		} finally {
			
			out.close(); // Close PrintWriter
		}
	}
 
	public int getCompareCount(Connection connection) {
		String query = "SELECT COUNT(ID) as COMPARE_COUNT FROM events_export WHERE (UPDATED_AT/1000) > ((CREATED_AT /1000) + 60);";
		int compareCount = 0;
		try {
			PreparedStatement st = connection.prepareStatement(query);
 
			ResultSet rs = st.executeQuery();
 
			while (rs.next()) {
				compareCount = rs.getInt("COMPARE_COUNT");
			}
			rs.close();
			st.close();
			DatabaseConnection.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return compareCount;
	}
	

 
}
 