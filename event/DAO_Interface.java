package com.event;

import java.util.List;

public interface DAO_Interface {
	int rowAffected = 0;

	void insertRows(List<String[]> MainList);
}
