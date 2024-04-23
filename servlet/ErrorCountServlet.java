package com.servlet;

import java.io.BufferedReader;
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

@WebServlet("/errorCount")
public class ErrorCountServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
//		response.setContentType("text/html");
		
		DAO_Impl dbConnection = new DAO_Impl();


		
		StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }

        // Parse the JSON data
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(requestBody.toString(), JsonObject.class);

        // Extract values from JSON
        String status = jsonObject.get("status").getAsString();
		
		int errorCount = 0;
		PrintWriter out = response.getWriter();
//		out.println("<h1>Id Before Count: " + idCount + "</h1>");
		
		try {
			errorCount = getErrorCount(dbConnection.getConn(), status);
			response.setContentType("application/json");
			String json = gson.toJson(errorCount);
			response.getWriter().write(json);

//			out.println("<h1>Error Count: " + errorCount + "</h1>");
		} catch (Exception e) {
//			out.println("<h1>Hello, in the catch Servlet!</h1>");
			out.println(e.getMessage());
		} finally {
//			out.println("<h1>boom boom</h1>");
			out.close(); // Close PrintWriter
		}
	}
	
	

//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		response.setContentType("text/html");
//		String status = request.getParameter("status");
//		DAO_Impl dbConnection = null;
//		try {
//			dbConnection = new DAO_Impl();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//		
//		int errorCount = 0;
//		PrintWriter out = response.getWriter();
////		out.println("<h1>Id Before Count: " + idCount + "</h1>");
//
//		try {
//			errorCount = getErrorCount(dbConnection.getConn(), status);
//
//			out.println("<h1>Error Count: " + errorCount + "</h1>");
//		} catch (Exception e) {
//			out.println("<h1>Hello, in the catch Servlet!</h1>");
//			out.println(e.getMessage());
//		} finally {
//			out.println("<h1>boom</h1>");
//			out.close(); // Close PrintWriter
//		}
//	}
	
	public int getErrorCount(Connection connection, String status) {
		String query = "SELECT COUNT(ID) AS ERROR_COUNT FROM events_export WHERE STATUS = ? AND ERROR_MESSAGE='' ";
		int errorCount = 0;
		
		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setString(1, status);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				errorCount = rs.getInt("ERROR_COUNT");
			}
			rs.close();
			st.close();
			DatabaseConnection.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return errorCount;
	}
}
