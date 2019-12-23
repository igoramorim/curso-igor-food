package com.mycompany.igorfood.testes.spark;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.spark.sql.SparkSession;

public class SparkUtils {
	
	public static void renameFile(SparkSession spark, String oldPath, String newPath) {
		
		FileSystem fs;
		try {
			Configuration hadoopConfiguration = spark.sparkContext().hadoopConfiguration();
			fs = FileSystem.get(hadoopConfiguration);
			FileUtil.copyMerge(fs, new Path(oldPath), fs, new Path(newPath), true, hadoopConfiguration, null);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static String getLocalDateTimeFormatted() {
		LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss");
		return now.format(formatter);
	}
	
	public static String getOutputPath() {
		String nowFormated = SparkUtils.getLocalDateTimeFormatted();
		
		StringBuilder sb = new StringBuilder();
		String newPathString = sb.append(SparkConstants.PATH_MERGED)
				.append("/")
				.append(SparkConstants.FILE_PREFIX)
				.append("-")
				.append(nowFormated)
				.append(SparkConstants.FILE_GZIP_EXT)
				.toString();
		
		return newPathString;
	}
}
