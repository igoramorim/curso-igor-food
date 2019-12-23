package com.mycompany.igorfood.testes.spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class TestesSparkReadCSV {

	public static void main(String[] args) {
		
		SparkSession spark = SparkSession.builder()
    			.appName("teste spark")
    			.master("local")
    			.getOrCreate();

		Dataset<Row> data = spark.read().format("csv").load("C:/Users/RE.03964/teste/teste.csv");
		
		data.write()
    	.format("com.databricks.spark.csv")
    	.option("header", "true")
    	.option("delimiter", ",")
    	.save("C:/Users/RE.03964/teste/largecsv");
	}

}
