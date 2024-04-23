package com.event;

import java.sql.Connection;

public class Main {

	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();
		CustomFileReader reader = new CustomFileReader();
		reader.readList();
//        
        IdCount idcount = new IdCount();
//        idcount.getIdCount(reader.getMainList());
        
        System.out.println(idcount.getIdCount(reader.getMainList()));
//
//        GroupBy groupBy = new GroupBy();
//        groupBy.getGroupBy(reader.getMainList());
//
//        ErrorReport errorReport = new ErrorReport();
//        errorReport.getErrorCount(reader.getMainList());
//
//        EpochTimeReport eTimeReport = new EpochTimeReport();
//        eTimeReport.getErrorReportMap(reader.getMainList());
//		
//        CompareCreatedAt compareCreatedAt = new CompareCreatedAt();
//        compareCreatedAt.getIds(reader.getMainList());    

//       Connection dbConnection = DatabaseConnection.getConnection();

		DAO_Impl dbConnection = new DAO_Impl();

//		dbConnection.insertRows(reader.getMainList());

		long endTime = System.currentTimeMillis();
		System.out.println("Execution time : " + (endTime - startTime));

	}

}
