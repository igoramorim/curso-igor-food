package com.mycompany.igorfood.testes.spark;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkFileCSV implements SparkFile {
	
	@Override
	public void generateFile(SparkSession spark, Dataset<Row> data, String outputPath) {
		
		data
//		 .repartition(1)
			.write()
			.format("com.databricks.spark.csv")
			.option("header", "true")
			.option("delimiter", ",")
			.option("codec", "org.apache.hadoop.io.compress.BZip2Codec")
//			.option("compression","bzip2")
			.save(SparkConstants.PATH_TEMP);
		
		SparkUtils.renameFile(spark, SparkConstants.PATH_TEMP, outputPath);

	}
	
}
