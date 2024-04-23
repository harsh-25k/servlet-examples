package com.event;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class EpochTimeReport {
	private int CREATED_AT;

	EpochTimeReport() {
		Properties props = new Properties();
		try (InputStream in = this.getClass().getResourceAsStream("/config.properties")) {

			props.load(in);
			CREATED_AT = Integer.parseInt(props.getProperty("CREATED_AT"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private TreeMap<String, Integer> dateCountMap = new TreeMap<>();

	private void generateEpochTimeReport(List<String[]> MainList) throws Exception {
		String values[];

		for (int i = 0; i < MainList.size(); i++) {
			values = MainList.get(i);

			long epochTime = (long) Double.parseDouble(values[CREATED_AT]);

			Date date = new Date(epochTime);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

			String formattedDate = formatter.format(date);

			if (dateCountMap.containsKey(formattedDate)) {
				dateCountMap.put(formattedDate, dateCountMap.get(formattedDate) + 1);
			} else {
				dateCountMap.put(formattedDate, 1);
			}

		}

		// System.out.println(dateCountMap.get("13/10/2023"));

		System.out.println(dateCountMap);
	}

	public TreeMap<String, Integer> getErrorReportMap(List<String[]> MainList) throws Exception {
		generateEpochTimeReport(MainList);
		return dateCountMap;
	}
}
