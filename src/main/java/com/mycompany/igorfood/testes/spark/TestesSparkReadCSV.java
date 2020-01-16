package com.mycompany.igorfood.testes.spark;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.mycompany.igorfood.testes.model.ModelTest;

public class TestesSparkReadCSV {

	public static void main(String[] args) {
		
		SparkSession spark = SparkSession.builder()
    			.appName("teste spark")
    			.master("local")
    			.getOrCreate();

		System.out.println("############################################");
		Dataset<Row> datasetFile = spark.read().format("json").load("C:/Users/RE.03964/Desktop/teste/salesReport-2020-01-01.json");
//		datasetFile.show();
		
		Dataset<Row> datasetMock = getDatasetMock(spark);
//		datasetMock.show();
		
//		long count = datasetFile.except(datasetMock).count();
		
//		long count = datasetFile.except(datasetMock).union(datasetMock.except(datasetFile)).count();
		
//		datasetFile.groupBy(sorted())
		

//		System.out.println(count);
		System.out.println("datasetMock: " + datasetMock.count());
		System.out.println("datasetFile: " + datasetFile.count());
		
		System.out.println("############################################");
		
//		data.write()
//    	.format("com.databricks.spark.csv")
//    	.option("header", "true")
//    	.option("delimiter", ",")
//    	.save("C:/Users/RE.03964/teste/largecsv");
	}
	
	private static Dataset<Row> getDatasetMock(SparkSession sparkSession) {
		List<ModelTest> dataList = new ArrayList<>();
		dataList.add(new ModelTest("teste 1", "teste 2", "teste 3", "teste 4", "teste 5"));
		dataList.add(new ModelTest("teste 1", "teste 2", "teste 3", "teste 4", "teste 5"));
		dataList.add(new ModelTest("teste 1", "teste 2", "teste 3", "teste 4", "teste 5"));
		Dataset<Row> datasetMock = sparkSession.createDataFrame(dataList, ModelTest.class);
		return datasetMock;
	}

}
