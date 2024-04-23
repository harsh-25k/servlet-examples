package com.event;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ErrorReport {
	private int ERROR_MESSAGE, STATUS;
	private int count;

	ErrorReport() {
		Properties props = new Properties();
		try (InputStream in = this.getClass().getResourceAsStream("/config.properties")) {

			props.load(in);
			ERROR_MESSAGE = Integer.parseInt(props.getProperty("ERROR_MESSAGE"));
			STATUS = Integer.parseInt(props.getProperty("STATUS"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void generateErrorReport(List<String[]> MainList) throws Exception {
		String values[];

		System.out.println("The errors with following Ids don't have an error message: ");
		for (int i = 0; i < MainList.size(); i++) {
			values = MainList.get(i);

			if (values[STATUS].isEmpty())
				continue;

			else if (values[STATUS].equals("error")) {
				if (values[ERROR_MESSAGE].equals("")) {
					count++;
				}
			}
		}
		System.out.println("The total no. of errors without message are : " + count);

	}

	public int getErrorCount(List<String[]> MainList) throws Exception {
		generateErrorReport(MainList);
		return count;
	}
}
