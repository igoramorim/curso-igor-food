package com.mycompany.igorfood.testes.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;

public class ConvertJavaRddToDataset {

	public static void main(String[] args) {

		JavaSparkContext sc = new JavaSparkContext(new SparkConf().setAppName("SparkJdbcDs")
				.set("spark.hadoop.validateOutputSpecs", "false") // overwrite when saveAsTextFile
				.setMaster("local[*]"));

		SparkSession spark = SparkSession.builder().appName("JavaTokenizerExample").getOrCreate();
		SQLContext sqlContext = new SQLContext(spark);
		
		JavaRDD<Name> initialRDD = spark.read()
				.textFile("C:/Users/RE.03964/Desktop/heindall/nomes.txt")
				.javaRDD()
				.map(new Function<String, Name>() {
					@Override
					public Name call(String data) throws Exception {
						String[] parts = data.split(",");
						return new Name(parts[0], parts[1]);
					}
					
				});

		Dataset<Row> dataset = sqlContext.createDataFrame(initialRDD, Name.class);
		dataset.show();
		
	}

}
