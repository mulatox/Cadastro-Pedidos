package com.desktop.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.desktop.database.CidadeEstadoDao;
import com.desktop.database.ClienteDao;
import com.desktop.model.CidadeEstado;
import com.desktop.model.Cliente;

public class TesteHibernate {

	public static void main(String args[]) throws IOException, InvalidFormatException {
		File file = new File("./DesktopTeste/relatorios/Cidades.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		OPCPackage pkg = OPCPackage.open(inputStream);

		XSSFWorkbook wb = new XSSFWorkbook(pkg);
		XSSFSheet plan1 = wb.getSheetAt(0);
		XSSFRow row = null;
		CidadeEstado cidade = null;
		CidadeEstadoDao dao = new CidadeEstadoDao();
		for (int i = 1; i <= 55; i++) {
			row = plan1.getRow(i);
			Iterator<Cell> cellIterator = row.cellIterator();
			cidade = new CidadeEstado();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				
				if (cell != null) {
					
					 
					 if(cell.getColumnIndex()==1)
					 {
						 cidade.setCidade(cell.getStringCellValue());
					 }
					 
					 else if(cell.getColumnIndex()==2)
					 {
						 cidade.setEstado(cell.getStringCellValue());
					 }
					
				}
			}
			 dao.inserir(cidade);
		}

	}

}
