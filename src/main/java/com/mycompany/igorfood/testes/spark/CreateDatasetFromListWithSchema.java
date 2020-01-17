package com.mycompany.igorfood.testes.spark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.spark.sql.functions.col;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class CreateDatasetFromListWithSchema {

	public static void main(String[] args) {
		
		Logger.getLogger("org.apache").setLevel(Level.WARN);

		Dataset<Row> csvDataSet = null;
		
		try {
//			System.setProperty("hadoop.home.dir", "D:\\AI matching\\winutil");
			JavaSparkContext sc = new JavaSparkContext(new SparkConf().setAppName("SparkJdbcDs").setMaster("local[*]"));
			SparkSession spark = SparkSession.builder().appName("JavaTokenizerExample").getOrCreate();
			SQLContext sqlContext = new SQLContext(spark);
			
			List<Row> data = Arrays.asList(
					RowFactory.create("405-048011-62815", "CRC Industries"),
					RowFactory.create("630-0746","Dixon value"),
					RowFactory.create("4444-444","3M INdustries"),
					RowFactory.create("555-55","Dixon coupling valve"));
			
			StructType schema = new StructType(new StructField[] {new StructField("label1", DataTypes.StringType, false,Metadata.empty()),
					new StructField("sentence1", DataTypes.StringType, false,Metadata.empty()) });

			Dataset<Row> dataset = spark.createDataFrame(data, schema);
			dataset.show();

//			List<String> listStrings = new ArrayList<String>();
//			listStrings.add("405-048011-62815");
//			listStrings.add("630-0746");
//			Dataset<Row> matchFound1=sentenceDataFrame.filter(col("label1").isin(listStrings.stream().toArray(String[]::new)));
//			sentenceDataFrame.show();
//			matchFound1.show();
//			listStrings.clear();
//			listStrings.add("222-2222-5555");
//			listStrings.add("7777-88886");

//			StringIndexer indexer = new StringIndexer()
//			  .setInputCol("label1")
//			  .setOutputCol("label1Index");
//			Dataset<Row> Dataset1 = indexer.fit(matchFound1).transform(matchFound1);
			//Dataset1.show();


//			List<Row> data2 = Arrays.asList(
//			RowFactory.create("222-2222-5555", "Tata"),
//			RowFactory.create("7777-88886","WestSide"),
//			RowFactory.create("22222-22224","Reliance"),
//			RowFactory.create("33333-3333","V industries"));
//
//			StructType schema2 = new StructType(new StructField[] {new StructField("label2", DataTypes.StringType, false,Metadata.empty()),
//			new StructField("sentence2", DataTypes.StringType, false,Metadata.empty()) });
//
//			Dataset<Row> sentenceDataFrame2 = spark.createDataFrame(data2, schema2);
//			
//			sentenceDataFrame.show();
//			sentenceDataFrame2.show();
			
//			Dataset<Row> finalDataset = sentenceDataFrame.join(sentenceDataFrame2, sentenceDataFrame.col("label1").equalTo(sentenceDataFrame2.col("label1")));
//			Dataset<Row> finalDataset = sentenceDataFrame.unionAll(sentenceDataFrame2);
//			finalDataset.show();

//			Dataset<Row> matchFound2=sentenceDataFrame2.filter(col("label2").isin(listStrings.stream().toArray(String[]::new)));
//			matchFound2.show();

//			StringIndexer indexer1 = new StringIndexer()
//			  .setInputCol("label2")
//			  .setOutputCol("label2Index");
//			Dataset<Row> Dataset2 = indexer1.fit(matchFound2).transform(matchFound2);
//			//Dataset2.show();
//			Dataset<Row> Finalresult = Dataset1.join(Dataset2 , Dataset1.col("label1Index").equalTo(Dataset2.col("label2Index"))).drop(Dataset1.col("label1Index")).drop(Dataset2.col("label2Index"));
//					Finalresult.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
