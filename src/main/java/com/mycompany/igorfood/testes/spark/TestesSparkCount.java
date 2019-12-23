package com.mycompany.igorfood.testes.spark;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;

import com.mycompany.igorfood.domain.model.Cozinha;
import com.mycompany.igorfood.domain.model.Restaurante;

public class TestesSparkCount {

	public static void main(String[] args) {
		
		// configuração do Spark
	    SparkConf conf = new SparkConf().setMaster("local").setAppName("BusProcessor");
	    JavaSparkContext ctx = new JavaSparkContext(conf);
	     
	    JavaRDD<String> linhas = ctx.textFile("C:/Users/RE.03964/teste/teste.txt");
	    long numeroLinhas = linhas.count();
	    
	    System.out.println("################################" + numeroLinhas);
	    
		ctx.close();

	}
	
}
