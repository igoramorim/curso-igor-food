package com.mycompany.igorfood.testes.spark.data;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

public class GenerateDataCSV {

	private static final String FILE_NAME = "C:/Users/RE.03964/teste/csv-load-data-2/file1-c.csv";
	private static final int COLUMNS = 48;
	private static final int COLUMN_LENGTH = 8;
	private static final int RECORDS = 787239;
	
	public static void main(String[] args) throws IOException {
		
		RandomAccessFile stream = new RandomAccessFile(FILE_NAME, "rw");
	    FileChannel channel = stream.getChannel();
	    
	    String value = getAllRows();
	    byte[] strBytes = value.getBytes();
	    
	    ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
	    buffer.put(strBytes);
	    buffer.flip();
	    
	    channel.write(buffer);
	    stream.close();
	    channel.close();
	}
	
	public static String getAllRows() {
		List<String> list = new ArrayList<String>(RECORDS);
	    for (int i = 0; i < RECORDS; i++) {
	        list.add(getRow());
	    }
	    
	    String allRows = String.join("\n", list);
	    return allRows;
	}
	
	public static String getRow() {
		List<String> list = new ArrayList<String>(COLUMNS);
	    for (int i = 0; i < COLUMNS; i++) {
	        list.add(getRandomString());
	    }
	    
	    String row = String.join(",", list);
	    return row;
	}
	
	public static String getRandomString() {
	    boolean useLetters = true;
	    boolean useNumbers = false;

	    return RandomStringUtils.random(COLUMN_LENGTH, useLetters, useNumbers);
	}

}
