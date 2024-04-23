package com.event;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class CompareCreatedAt {
	private int CREATED_AT;
	private int UPDATED_AT;
	private int ID;

	CompareCreatedAt() {
		Properties props = new Properties();
		try (InputStream in = this.getClass().getResourceAsStream("/config.properties")) {

			props.load(in);
			CREATED_AT = Integer.parseInt(props.getProperty("CREATED_AT"));
			UPDATED_AT = Integer.parseInt(props.getProperty("UPDATED_AT"));
			ID = Integer.parseInt(props.getProperty("ID"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String> ids = new ArrayList<>();

	public void doCompareCreatedAt(List<String[]> MainList) throws Exception {

		String values[];

		for (int i = 0; i < MainList.size(); i++) {
			values = MainList.get(i);

			if (values[CREATED_AT].equals("") || values[UPDATED_AT].equals(""))
				continue;

			long epochTimeCreatedAt = (long) Double.parseDouble(values[CREATED_AT]);
			long epochTimeUpdatedAt = (long) Double.parseDouble(values[UPDATED_AT]);

			if (epochTimeUpdatedAt > epochTimeCreatedAt + 60) {

				ids.add(values[ID]);
			}

		}

		System.out.println(ids);

	}

	public List<String> getIds(List<String[]> MainList) throws Exception {
		doCompareCreatedAt(MainList);
		return ids;
	}

}
