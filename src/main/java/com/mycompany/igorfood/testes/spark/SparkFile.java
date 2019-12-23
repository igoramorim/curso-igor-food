package com.mycompany.igorfood.testes.spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public interface SparkFile {
	
	void generateFile(SparkSession spark, Dataset<Row> data, String outputPath);
	
//	void renameFile(SparkSession spark, Path oldPath, Path newPath);

}
