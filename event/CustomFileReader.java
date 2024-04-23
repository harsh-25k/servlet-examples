package com.event;

import java.io.FileReader;
import java.util.*;

import com.opencsv.CSVReader;

public class CustomFileReader {
	private List<String[]> MainList = new ArrayList<>();

	public void readList() throws Exception {
		CSVReader reader = new CSVReader(new FileReader("src/main/resources/dataFile.csv"));
		String line[];

		String[] currentArray;
		while ((line = reader.readNext()) != null) {
			currentArray = line;

			MainList.add(line);
		}

	}

	public List<String[]> getMainList() throws Exception {

		return MainList;
	}
}
