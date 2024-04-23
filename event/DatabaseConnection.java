package com.event;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection{
	private static final String URL = "jdbc:mysql://localhost:3306/training";
	private static final String USERNAME = "****";
	private static final String PASSWORD = "****";
	private static Connection connection;

	public DatabaseConnection() {
	}

	public static Connection getConnection() throws Exception {
		if (connection == null || connection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				System.out.println("connection created");
//				response.getWriter().println("created connection");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	public static void closeConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();

			}
			System.out.println("Connection Closed Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
