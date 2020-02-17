package com.mycompany.igorfood.testes.spark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class ReadECCV {
	
	public static void main(String[] args) {
		
//		Logger.getLogger("org.apache").setLevel(Level.WARN);
		
		SparkConf conf = new SparkConf().setAppName("startingSpark").setMaster("local[*]");
		JavaSparkContext sc = new JavaSparkContext(conf);
		SparkSession spark = SparkSession.builder()
    			.appName("teste spark")
    			.master("local")
    			.getOrCreate();
		
		String pathFull = "C:/Users/RE.03964/Desktop/ECVVUP.20200211_145920.txt";
		String pathPart = "C:/Users/RE.03964/Desktop/ECVV-IGOR/file/part-00000";
		String pathTeste = "C:/Users/RE.03964/Desktop/ECVV-IGOR/teste.txt";
		
//		JavaRDD<String> initialRDD = sc.textFile(pathTeste);
//		initialRDD.foreach(line -> {
//			String[] split = line.split("\\|;\\|");
//			String nomeFantasia = split[13];
//			System.out.println("nome fantasia: " + nomeFantasia);
//		});
		
		Dataset<Row> datasetFile = spark.read()
										.format("csv")
										.option("delimiter", "|;|")
										.load(pathTeste);
		
		datasetFile.show();
		
//		Dataset<Row> select = datasetFile.select("_c6", "_c12", "_c13");
//		select.show();
		
//		initialRDD.saveAsTextFile("C:/Users/RE.03964/Desktop/ECVV-IGOR/file.txt");
		
		sc.close();
	}

}
