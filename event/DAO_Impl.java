package com.event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DAO_Impl implements DAO_Interface {
	private Connection connection;
	
	public Connection getConn() {
		return connection;
	}
	
	int id;
	double created_at;
	String created_by;
	String error_message;
	String error_response;
	String event_type;
	String name;
	String reference_id;
	String request_type;
	String status;
	String transaction_status_request;
	double updated_at;
	String url;
	String work_item_id;

	public DAO_Impl() {
		try {
			connection = DatabaseConnection.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public static DAO_Impl getInstance() {
//		try {
//		if (instance == null) {
//			instance = new DAO_Impl();
//		}
//		}
//		catch(Exception e) {
//			System.out.println(e);
//		}
//		return instance;
//	}

	public void insertRows(List<String[]> MainList) {
//		System.out.println("ok");
		String query = "INSERT INTO events_export " + "(ID,CREATED_AT,CREATED_BY,ERROR_MESSAGE,ERROR_RESPONSE,"
				+ "EVENT_TYPE,NAME,REFERENCE_ID,REQUEST_TYPE,STATUS,"
				+ "TRANSACTION_STATUS_REQUEST,UPDATED_AT,URL,WORK_ITEM_ID) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			int batchSize = 0;
			for (String[] record : MainList) {

				id = Integer.parseInt(record[0]);
				created_at = Double.parseDouble(record[1]);
				created_by = record[2];
				error_message = record[3];
				error_response = record[4];
				event_type = record[5];
				name = record[6];
				reference_id = record[7];
				request_type = record[8];
				status = record[9];
				transaction_status_request = record[10];
				updated_at = Double.parseDouble(record[11]);
				url = record[12];
				work_item_id = record[13];

				try {
					setPreparedStatement(preparedStatement, record);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//        preparedStatement.setInt(1, id);
//        preparedStatement.setDouble(2, created_at);
//        preparedStatement.setString(3, created_by);
//        preparedStatement.setString(4, error_message);
//        preparedStatement.setString(5, error_response);
//        preparedStatement.setString(6, event_type);
//        preparedStatement.setString(7, name);
//        preparedStatement.setString(8, reference_id);
//        preparedStatement.setString(9, request_type);
//        preparedStatement.setString(10, status);
//        preparedStatement.setString(11, transaction_status_request);
//        preparedStatement.setDouble(12, updated_at);
//        preparedStatement.setString(13, url);
//        preparedStatement.setString(14, work_item_id);

				preparedStatement.addBatch();

				if (++batchSize % 5000 == 0) {
					preparedStatement.executeBatch();
					System.out.println("Batch inserted successfully.");
				}

			}
			preparedStatement.executeBatch();
			System.out.println("Data inserted successfully from CSV.");

			DatabaseConnection.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void setPreparedStatement(PreparedStatement preparedStatement, String[] record) throws Exception {
		preparedStatement.setInt(1, id);
		preparedStatement.setDouble(2, created_at);
		preparedStatement.setString(3, created_by);
		preparedStatement.setString(4, error_message);
		preparedStatement.setString(5, error_response);
		preparedStatement.setString(6, event_type);
		preparedStatement.setString(7, name);
		preparedStatement.setString(8, reference_id);
		preparedStatement.setString(9, request_type);
		preparedStatement.setString(10, status);
		preparedStatement.setString(11, transaction_status_request);
		preparedStatement.setDouble(12, updated_at);
		preparedStatement.setString(13, url);
		preparedStatement.setString(14, work_item_id);
	}
	
//	public int getCount() {
//		String query = "SELECT COUNT(*) as ROW_COUNT FROM events_export";
//		int rowCount =0;
//		try {
//			PreparedStatement st = connection.prepareStatement(query);
//			
//			ResultSet rs = st.executeQuery();
//			
//			while(rs.next()) {
//				rowCount = rs.getInt("ROW_COUNT");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return rowCount;
//	}
	
//	public HashMap<String,Integer> getEventGroupBy(){
//		HashMap<String,Integer> eventCount = new HashMap<String, Integer>();
//		String query = "SELECT EVENT_TYPE FROM events_export";
//		try {
//			PreparedStatement st = connection.prepareStatement(query);
//			
//			ResultSet rs = st.executeQuery();
//			
//			
//			while(rs.next()) {
//				String event = rs.getString("EVENT_TYPE");
//			    eventCount.put(event, eventCount.getOrDefault(event, 0) + 1);
//				
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//		return eventCount;
//		
//	}
}
