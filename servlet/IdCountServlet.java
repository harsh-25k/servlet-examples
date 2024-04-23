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

@WebServlet("/idCount")
public class IdCountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		DAO_Impl dbConnection = new DAO_Impl();
		
		
		
		
		
		Integer idCount = 0;
		PrintWriter out = response.getWriter();
//		out.println("<h1>Id Before Count: " + idCount + "</h1>");

		try {
			idCount = getCount(dbConnection.getConn());
//			out.println("<h1>Id Count: " + idCount + "</h1>");
		} catch (Exception e) {
			out.println("<h1>Hello, in the catch Servlet!</h1>");
			out.println(e.getMessage());
		} 
		finally {
			Gson gson = new Gson();
			String json = gson.toJson(idCount);
			response.setContentType("application/json");
			
			response.getWriter().write(json);
			
		}
	}
	
	public int getCount(Connection connection) {
		String query = "SELECT COUNT(*) as ROW_COUNT FROM events_export";
		int rowCount =0;
		try {
			PreparedStatement st = connection.prepareStatement(query);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				rowCount = rs.getInt("ROW_COUNT");
			}
			rs.close();
			st.close();
			DatabaseConnection.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount;
	}
}
