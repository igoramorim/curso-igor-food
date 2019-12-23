package com.mycompany.igorfood.testes.spark.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressFile {

	public static void main(String[] args) throws IOException {
		String sourceFile = "C:/Users/RE.03964/teste/csv-load-data-2/file1-c.csv";
		
        FileOutputStream fos = new FileOutputStream("C:/Users/RE.03964/teste/csv-load-data-2/compressed.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(sourceFile);
        FileInputStream fis = new FileInputStream(fileToZip);
        
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);
        
        byte[] bytes = new byte[1024];
        int length;
        
        while((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        
        zipOut.close();
        fis.close();
        fos.close();
		
	}

}
