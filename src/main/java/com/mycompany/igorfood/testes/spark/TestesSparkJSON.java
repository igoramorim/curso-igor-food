package com.mycompany.igorfood.testes.spark;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.mycompany.igorfood.domain.model.Cozinha;

public class TestesSparkJSON {
	
	private static final int RECORD_COUNT = 10;

	public static void main(String[] args) throws IOException {
		
		SparkSession spark = SparkSession.builder()
    			.appName("teste spark")
    			.master("local")
    			.getOrCreate();
    
//	    Dataset<Row> jsonDF = spark.read().format("json").load("C:/Users/RE.03964/teste/teste.json");
//	    System.out.println("################################################" + jsonDF.count());
//	    jsonDF.show();
//	    jsonDF.write().json("C:/Users/RE.03964/teste/json");
	    
	    List<Cozinha> cozinhaList = getLargeCozinhaList();
	    Dataset<Row> cozinhaDF = spark.createDataFrame(cozinhaList, Cozinha.class);
//	    cozinhaDF.show();
	    
//	    cozinhaDF.toJSON().write().mode(SaveMode.Overwrite).json("C:/Users/RE.03964/teste/json");
//	    cozinhaDF.coalesce(1).write().json("C:/Users/RE.03964/teste/json");
	    	
//	    cozinhaDF.write()
//    	.format("com.databricks.spark.csv")
//    	.option("header", "true")
//    	.option("delimiter", ",")
//    	.save("C:/Users/RE.03964/teste/csv");
	    
//	    cozinhaDF.write().option("header", "true").option("delimiter", ",").csv("C:/Users/RE.03964/teste/csv");
	    
	    
	    Dataset<String> cozinhaString = cozinhaDF.toJSON();
	    
	    long count = cozinhaString.count();
	    cozinhaString
	    	.repartition(1)
	    	.javaRDD()
	    	.zipWithIndex()
	    	.map(t -> t._2 == 0 ? "[\n" + t._1 + "," : t._2 == count-1 ? t._1 + "\n]" : t._1 + ",")
	    	.saveAsTextFile("C:/Users/RE.03964/teste/jsonBonito");

	    FileSystem fs = FileSystem.get(spark.sparkContext().hadoopConfiguration());
	    fs.rename(new Path("C:/Users/RE.03964/teste/jsonBonito/part-00000"), new Path("C:/Users/RE.03964/teste/jsonBonito/newName.json"));
	    
	    spark.close();

	}
	
	public static List<String> getStringList() {
		List<String> list = new ArrayList<String>();
		list.add("Igor");
		list.add("Maria");
		list.add("Joao");
		list.add("Miriam");
		return list;
	}

	public static List<Cozinha> getCozinhaList() {

		List<Cozinha> list = new ArrayList<Cozinha>();
		Cozinha c1 = Cozinha.builder().id(1L).nome("Italiana").build();
		Cozinha c2 = Cozinha.builder().id(2L).nome("Japonesa").build();
		Cozinha c3 = Cozinha.builder().id(2L).nome("Francesa").build();
		list.add(c1);
		list.add(c2);
		list.add(c3);
		return list;
	}
	
	public static List<Cozinha> getLargeCozinhaList() {
		Cozinha c1 = Cozinha.builder().id(1L).nome("Italiana").build();
		
		List<Cozinha> list = new ArrayList<Cozinha>(RECORD_COUNT);
	    for (int i = 0; i < RECORD_COUNT; i++) {
	        list.add(c1);
	    }
	    
	    return list;
	}
	
}
 