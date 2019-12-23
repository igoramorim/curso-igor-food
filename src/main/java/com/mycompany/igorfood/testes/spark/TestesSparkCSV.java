package com.mycompany.igorfood.testes.spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class TestesSparkCSV {

	public static void main(String[] args) {
		SparkSession spark = SparkSession.builder()
    			.appName("teste spark")
    			.master("local")
    			.getOrCreate();
    
    Dataset<Row> jsonDF = spark.read().format("json").load("C:/Users/RE.03964/teste/teste.json");
    System.out.println("################################################" + jsonDF.count());
    
    jsonDF.show();
    
    jsonDF.write()
    	.format("com.databricks.spark.csv")
    	.option("header", "true")
    	.option("delimiter", ",")
    	.save("C:/Users/RE.03964/teste/csv");
    
	// ########################################################################################
		
	//    SparkConf conf = new SparkConf().setMaster("local").setAppName("BusProcessor");
		
	//    SparkSession sparkSession = SparkSession.builder()
	//    			.appName("teste spark")
	//    			.master("local")
	//    			.getOrCreate();
	//    
	//    SQLContext sqc = new SQLContext(sparkSession);
	//	
	//	List<Restaurante> restaurantes =  getRestaurantes();
		
	//	Dataset<Row> df = sqc.createDataset(restaurantes, evidence$5)

	}

}


