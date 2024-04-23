package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.event.DAO_Impl;
import com.event.DatabaseConnection;
import com.google.gson.Gson;

@WebServlet("/eventCount")
public class EventCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.setContentType("text/html");
		

		DAO_Impl dbConnection = new DAO_Impl();

		HashMap<String, Integer> eventCount = new HashMap<String, Integer>();
		PrintWriter out = response.getWriter();
//		out.println("<h1>Id Before Count: " + idCount + "</h1>");
//		ResultSet result = null;
		
		
//		try {
//			result = getEventGroupBy(dbConnection.getConnection());
//			while(result.next()) {
//				String eventType = result.getString("EVENT_TYPE");
//				Integer eventCount = result.getInt("EVENT_COUNT");
//				response.getWriter().println(eventType + " : " + eventCount);
//			}
//		} catch (Exception e) {
////			out.println("<h1>Hello, in the catch Servlet!</h1>");
////			out.println(e.getMessage());
//		}finally {
////			out.println("<h1>boom</h1>");
////			out.close(); // Close PrintWriter
//		}
		
		Gson gson = new Gson();
		
		try {
			eventCount = getEventGroupBy(dbConnection.getConn());
			response.setContentType("application/json");
			String json = gson.toJson(eventCount);
			response.getWriter().write(json);
//			out.println("<h1>Event Count: " + eventCount + "</h1>");
		} catch (Exception e) {
//			out.println("<h1>Hello, in the catch Servlet!</h1>");
			out.println(e.getMessage());
		} finally {
//			out.println("<h1>boom</h1>");
			out.close(); // Close PrintWriter
		}
	}
	
	public HashMap<String,Integer> getEventGroupBy(Connection connection){
		HashMap<String,Integer> eventCount = new HashMap<String, Integer>();
		String query = "SELECT EVENT_TYPE FROM events_export";
		ResultSet rs = null;
//		String query = "SELECT EVENT_TYPE, COUNT(*) as EVENT_COUNT FROM events_export group by EVENT_TYPE";
		try {
			PreparedStatement st = connection.prepareStatement(query);
			
			 rs = st.executeQuery();
			
			
			while(rs.next()) {
				String event = rs.getString("EVENT_TYPE");
			    eventCount.put(event, eventCount.getOrDefault(event, 0) + 1);
				
			}
			rs.close();
			st.close();
			DatabaseConnection.closeConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return eventCount;
		
	}
}
