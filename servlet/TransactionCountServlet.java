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
 
@WebServlet("/transactionCount")
 
public class TransactionCountServlet extends HttpServlet{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
 
		DAO_Impl dbConnection = new DAO_Impl();
 
		
		PrintWriter out = response.getWriter();
		ResultSet result = getTransactionResult(dbConnection.getConn());
		
 
		try {
			while(result.next()) {
				String eventDate = result.getString("CREATED_DATE");
				int eventCount = result.getInt("EVENT_COUNT");
				
				out.println(eventDate + " : " + eventCount);
				out.println("<br/>");
				
			}
		} catch (Exception e) {
			out.println("<h1>Hello, in the catch Servlet!</h1>");
			out.println(e.getMessage());
		} finally {
			out.close(); // Close PrintWriter
			
			DatabaseConnection.closeConnection();
		}
	}
	
	public ResultSet getTransactionResult(Connection connection) {
		String query = "SELECT DATE(FROM_UNIXTIME(CREATED_AT / 1000)) AS CREATED_DATE, COUNT(*) AS EVENT_COUNT FROM events_export GROUP BY DATE(FROM_UNIXTIME(CREATED_AT /1000)) ORDER BY DATE(FROM_UNIXTIME(CREATED_AT / 1000)) DESC";
		ResultSet rs = null;
	
		try {
			PreparedStatement st = connection.prepareStatement(query);
 
			 rs = st.executeQuery();
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
 
}