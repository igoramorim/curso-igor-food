package com.mycompany.igorfood.testes.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class ConvertJavaRddToDataset2 {

	public static void main(String[] args) {

		JavaSparkContext sc = new JavaSparkContext(new SparkConf().setAppName("SparkJdbcDs")
				.set("spark.hadoop.validateOutputSpecs", "false") // overwrite when saveAsTextFile
				.setMaster("local[*]"));

		SparkSession spark = SparkSession.builder().appName("JavaTokenizerExample").getOrCreate();
		SQLContext sqlContext = new SQLContext(spark);
		
		JavaRDD<String> initialRDD = spark.read()
				.textFile("C:/Users/RE.03964/Desktop/heindall/nomes.txt")
				.javaRDD();
		
		StructType schema = new StructType(new StructField[] {
				new StructField("nome", DataTypes.StringType, false, Metadata.empty()),
				new StructField("sobreName", DataTypes.StringType, false, Metadata.empty())
		});
		
		JavaRDD<Row> rowRDD = initialRDD.map(new Function<String, Row>() {
			@Override
			public Row call(String data) throws Exception {
				String[] parts = data.split(",");
				return RowFactory.create(parts[0], parts[1]);
			}
		});

		Dataset<Row> dataset = sqlContext.createDataFrame(rowRDD, schema);
		dataset.show();
		
	}

}
