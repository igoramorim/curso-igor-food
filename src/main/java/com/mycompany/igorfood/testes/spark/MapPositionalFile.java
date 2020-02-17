package com.mycompany.igorfood.testes.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;

public class MapPositionalFile {

	public static void main(String[] args) {

		JavaSparkContext sc = new JavaSparkContext(new SparkConf().setAppName("SparkJdbcDs")
				.set("spark.hadoop.validateOutputSpecs", "false") // overwrite when saveAsTextFile
				.setMaster("local[*]"));
		SparkSession spark = SparkSession.builder().appName("JavaTokenizerExample").getOrCreate();
		SQLContext sqlContext = new SQLContext(spark);
		
		Dataset<Row> dataset = spark.read()
				.format("csv")
				.option("header", "true")
				.load("C:/Users/RE.03964/Desktop/heindall/nomes.csv");
		
//		dataset.map(func, evidence$4);
		
	}

}
