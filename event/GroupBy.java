package com.event;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class GroupBy {
	int EVENT_TYPE;

	GroupBy() {
		Properties props = new Properties();
		try (InputStream in = this.getClass().getResourceAsStream("/config.properties")) {

			props.load(in);
			EVENT_TYPE = Integer.parseInt(props.getProperty("EVENT_TYPE"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private HashMap<String, Integer> groupedDataMap = new HashMap<>();

	public void doGroupBy(List<String[]> MainList) throws Exception {

		String values[];
		for (int i = 0; i < MainList.size(); i++) {
			values = MainList.get(i);

			if (values[EVENT_TYPE].isEmpty())
				continue;

			else if (groupedDataMap.containsKey(values[5])) {
				groupedDataMap.put(values[5], groupedDataMap.get(values[5]) + 1);
			}

			else {
				groupedDataMap.put(values[5], 1);
			}
		}

		System.out.println(groupedDataMap);

	}

	public HashMap<String, Integer> getGroupBy(List<String[]> MainList) throws Exception {
		doGroupBy(MainList);
		return groupedDataMap;
	}

}
