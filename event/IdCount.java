package com.event;

import java.util.*;

public class IdCount {

	private int count;

	private void countIds(List<String[]> MainList) throws Exception {

		for (int i = 0; i < MainList.size(); i++) {
			count++;
		}

//		System.out.println("The number of ids are: " + count);

	}

	public int getIdCount(List<String[]> MainList) throws Exception {
		countIds(MainList);
		return count;
	}
}
