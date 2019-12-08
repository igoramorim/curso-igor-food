package com.mycompany.igorfood.testes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mycompany.igorfood.testes.model.Aluno;

public class TestesCriaExcel {
	
	private static final String fileName = "c:/users/igor/teste/novo.xlsx";
	
	public static void main(String[] args) {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheetAlunos = workbook.createSheet("Alunos");
		
		List<Aluno> listAlunos = new ArrayList<Aluno>();
		listAlunos.add(new Aluno("Eduardo", "3132", 1, 6));
		listAlunos.add(new Aluno("Maria", "7513", 3, 8));
		listAlunos.add(new Aluno("Joao", "9834", 7, 9));
		listAlunos.add(new Aluno("Joana", "8930", 7, 5));
		listAlunos.add(new Aluno("Batman", "0954", 9, 4));
		
		int rowNum = 0;
		for (Aluno aluno: listAlunos) {
			Row row = sheetAlunos.createRow(rowNum++);
			int cellNum = 0;
			
			Cell cellNome = row.createCell(cellNum++);
			cellNome.setCellValue(aluno.getNome());
			
			Cell cellRa = row.createCell(cellNum++);
			cellRa.setCellValue(aluno.getRa());
			
			Cell cellNota1 = row.createCell(cellNum++);
			cellNota1.setCellValue(aluno.getNota1());
			
			Cell cellNota2 = row.createCell(cellNum++);
			cellNota2.setCellValue(aluno.getNota2());
			
			Cell cellMedia = row.createCell(cellNum++);
			cellMedia.setCellValue((aluno.getNota1() + aluno.getNota2()) / 2);
		}
		
		try {
			FileOutputStream out = new FileOutputStream(new File(fileName));
			workbook.write(out);
			out.close();
			System.out.println("Arquivo excel criado com sucesso");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Arquivo não encontrado");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro na edição do arquivo");
		}
	}

}
