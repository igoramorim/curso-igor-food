package com.mycompany.igorfood.testes.spark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.avg;
import static org.apache.spark.sql.functions.count;
import static org.apache.spark.sql.functions.max;
import static org.apache.spark.sql.functions.min;
import static org.apache.spark.sql.functions.sum;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import scala.Tuple2;

public class DistinctPhoneNumbers {

	public static void main(String[] args) {
		
		Logger.getLogger("org.apache").setLevel(Level.WARN);

		try {
			JavaSparkContext sc = new JavaSparkContext(new SparkConf().setAppName("SparkJdbcDs").setMaster("local[*]"));
			SparkSession spark = SparkSession.builder().appName("JavaTokenizerExample").getOrCreate();
			SQLContext sqlContext = new SQLContext(spark);
			
			List<Row> data1 = Arrays.asList(
					RowFactory.create("1", "1111"),
					RowFactory.create("1", "1112"),
					RowFactory.create("2", "2221"),
					RowFactory.create("2", "2223"),
					RowFactory.create("3", "3333"));
			
			List<Row> data2 = Arrays.asList(
					RowFactory.create("1", "1111"),
					RowFactory.create("1", "1112"),
					RowFactory.create("2", "2221x"),
					RowFactory.create("2", "2223"),		
					RowFactory.create("3", "3333"));
			
			
			StructType schema = new StructType(new StructField[] {
					new StructField("id", DataTypes.StringType, false, Metadata.empty()),
					new StructField("phone", DataTypes.StringType, false, Metadata.empty())
			});

			Dataset<Row> dataset1 = spark.createDataFrame(data1, schema);
			Dataset<Row> dataset2 = spark.createDataFrame(data2, schema);
			
//			dataset1.show();
//			dataset2.show();
			
			Dataset<Row> join = dataset1.join(dataset2);
			join.show();
			System.out.println("########################");
			System.out.println(join.count());
			System.out.println("########################");
			
//			join.groupBy("id").;

//			List<Row> collectAsList = dataset1.groupBy("id").agg(
//					count("id")
//					).collectAsList();
//			collectAsList.forEach(System.out::println);
			
			
//			dataset1.javaRDD()
//			 .mapToPair(rawValue -> new Tuple2<>(rawValue.toString().split(",")[0], rawValue.toString().split(",")[1]))
//			 .foreach(v -> System.out.println(v));

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
