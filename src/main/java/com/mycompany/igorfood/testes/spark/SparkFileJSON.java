package com.mycompany.igorfood.testes.spark;

import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkFileJSON implements SparkFile {
	
	@Override
	public void generateFile(SparkSession spark, Dataset<Row> data, String outputPath) {
		
		Dataset<String> dataJSON = data.toJSON();
		
		long count = dataJSON.count();
		dataJSON
			// .repartition(1)
			.javaRDD()
			.zipWithIndex()
			.map(t -> t._2 == 0 ? "[\n" + t._1 + "," : t._2 == count-1 ? t._1 + "\n]" : t._1 + ",")
//			.saveAsTextFile(SparkConstants.PATH_TEMP);
			.saveAsTextFile(SparkConstants.PATH_TEMP, GzipCodec.class);

		SparkUtils.renameFile(spark, SparkConstants.PATH_TEMP, outputPath);
		
	}

}
