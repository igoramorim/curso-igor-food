package com.mycompany.igorfood.testes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mycompany.igorfood.testes.model.Aluno;

public class TestesAbreExcel {
	
	private static final String fileName = "C:/Users/igor/teste/teste-apache-poi.xlsx";
	
	public static void main(String[] args) throws IOException {
		
		List<Aluno> listAlunos = new ArrayList<Aluno>();
		
		try {
			FileInputStream file = new FileInputStream(new File(fileName));
			
			XSSFWorkbook workbook = new XSSFWorkbook(file); 
			
			// primeira aba do arquivo excel
			XSSFSheet sheetAlunos = workbook.getSheetAt(0);
			
			Iterator<Row> rowIterator = sheetAlunos.iterator();
			
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				
				Aluno aluno = new Aluno();
				
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getColumnIndex()) {
					case 0:
						aluno.setNome(cell.getStringCellValue());
						break;
					case 1:
						aluno.setRa(String.valueOf(cell.getNumericCellValue()));
						break;
					case 2:
						aluno.setNota1(cell.getNumericCellValue());
						break;
					case 3:
						aluno.setNota2(cell.getNumericCellValue());
						break;
					case 4:
						aluno.setMedia(cell.getNumericCellValue());
						break;
					}
				}
				
				listAlunos.add(aluno);
			}
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Arquivo não encontrado");
		}
		
		listAlunos.stream()
			.filter(a -> !a.getNome().isEmpty())
			.forEach(System.out::println);
	}

}
