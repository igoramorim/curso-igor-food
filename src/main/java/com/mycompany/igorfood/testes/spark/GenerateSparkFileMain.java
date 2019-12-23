package com.mycompany.igorfood.testes.spark;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.DataTypes;
import static org.apache.spark.sql.functions.udf;
import static org.apache.spark.sql.functions.col;

import com.mycompany.igorfood.domain.model.Cozinha;
import com.mycompany.igorfood.domain.model.Restaurante;

public class GenerateSparkFileMain {
	
	private static final int RECORD_COUNT = 1000000;

	public static void main(String[] args) {
		
		SparkSession spark = SparkSession.builder()
    			.appName("teste spark")
    			.master("local[*]")
    			.config("spark.network.timeout", "600s")
    			.getOrCreate();
		
//		List<Cozinha> list = getLargeCozinhaList();
//	    Dataset<Row> anotherDF = spark.createDataFrame(list, Cozinha.class);
	    
		Dataset<Row> listDF = getDataFromLargeCSV(spark);
		
	    SparkFile sparkFile = new SparkFileCSV();
	    String outputPath = SparkUtils.getOutputPath();
	    
	    sparkFile.generateFile(spark, listDF, outputPath);
	    
	    System.out.println("######################################");
//	    System.out.println(listDF.count());
//	    listDF.groupBy(col("_c44")).count().show();
//	    listDF.describe().show();
	    listDF.show();
//	    System.out.println(listDF.javaRDD().getNumPartitions());
	    
	    listDF.groupBy("_c6", "_c44", "_c45").count().show();
	    System.out.println("######################################");
	    
	    spark.close();
	}

	public static Dataset<Row> getDataFromLargeCSV(SparkSession spark) {
		
		Dataset<Row> data = spark.read()
				.format("csv")
				.option("delimiter", ";")
//				.load("C:/Users/RE.03964/teste/csv-load-data-2/*.csv");
				.load("C:/Users/RE.03964/teste/load-transvv/TRANSVV.TXT");
		
		return data;
	}
	
	public static List<Cozinha> getLargeCozinhaList() {
		Cozinha c1 = Cozinha.builder().id(1L).nome("Italiana").build();
		
		List<Cozinha> list = new ArrayList<Cozinha>(RECORD_COUNT);
	    for (int i = 0; i < RECORD_COUNT; i++) {
	        list.add(c1);
	    }
	    
	    return list;
	}
	
	public static List<Restaurante> getLargeRestauranteList() {
		Cozinha c1 = Cozinha.builder().id(1L).nome("Italiana").build();
		Restaurante r1 = Restaurante.builder().id(1L).nome("Joao e Maria").taxaFrete(new BigDecimal("1.50")).cozinha(c1).build();
		
		List<Restaurante> list = new ArrayList<Restaurante>(RECORD_COUNT);
	    for (int i = 0; i < RECORD_COUNT; i++) {
	        list.add(r1);
	    }
	    
	    return list;
	}
}
