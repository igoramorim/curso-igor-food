package com.mycompany.igorfood.testes.spark;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.mycompany.igorfood.domain.model.Cozinha;
import com.mycompany.igorfood.domain.model.Restaurante;

public class TestesSparkMultiJSON {
	
	private static final int RECORD_COUNT = 10;

	public static void main(String[] args) {

		SparkSession spark = SparkSession.builder()
    			.appName("teste spark")
    			.master("local")
    			.getOrCreate();
		
		List<Restaurante> restauranteList = getLargeRestauranteList();
	    
		Dataset<Row> restauranteDF = spark.createDataFrame(restauranteList, Restaurante.class);
	    
		Dataset<String> restauranteString = restauranteDF.toJSON();
	    
	    long count = restauranteString.count();
	    
	    restauranteString
	    	.repartition(1)
	    	.javaRDD()
	    	.zipWithIndex()
	    	.map(t -> t._2 == 0 ? "[\n" + t._1 + "," : t._2 == count-1 ? t._1 + "\n]" : t._1 + ",")
	    	.saveAsTextFile("C:/Users/RE.03964/teste/multiJsonBonito");
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
