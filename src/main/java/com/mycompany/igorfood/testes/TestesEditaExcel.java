package com.mycompany.igorfood.testes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestesEditaExcel {
	
	public static final String fileName = "c:/users/igor/teste/novo.xlsx";
	
	public static void main(String[] args) throws IOException {
		
		try {
			FileInputStream file = new FileInputStream(new File(fileName));
			
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheetAlunos = workbook.getSheetAt(0);
			
			// aumenta a media em 1 ponto
			for (int i = 0; i < sheetAlunos.getPhysicalNumberOfRows(); i++) {
				Row row = sheetAlunos.getRow(i);
				Cell cellMedia = row.getCell(4);
				cellMedia.setCellValue(cellMedia.getNumericCellValue() + 1);
			}
			
			file.close();
			
			FileOutputStream out = new FileOutputStream(new File(fileName));
			workbook.write(out);
			out.close();
			System.out.println("Arquivo excel editado com sucesso");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Arquivo não encontrado");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro na edição do arquivo");
		}
	}

}
