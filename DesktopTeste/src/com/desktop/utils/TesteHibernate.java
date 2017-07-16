package com.desktop.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.desktop.database.CidadeEstadoDao;
import com.desktop.database.ClienteDao;
import com.desktop.database.ParcelaDao;
import com.desktop.database.VendaDao;
import com.desktop.model.CidadeEstado;
import com.desktop.model.Cliente;
import com.desktop.model.Parcela;
import com.desktop.model.Venda;

public class TesteHibernate {

	public static void main(String args[]) throws IOException, InvalidFormatException, ParseException {
	//	File file = new File("./DesktopTeste/relatorios/Pedidos.xlsx");
		//FileInputStream inputStream = new FileInputStream(file);
		//OPCPackage pkg = OPCPackage.open(inputStream);
		//XSSFWorkbook wb = new XSSFWorkbook(pkg);
		//XSSFSheet plan1 = wb.getSheetAt(0);
	//	XSSFRow row = null;
		//Venda pedido = null;
		//ClienteDao dao = new ClienteDao();
		VendaDao daoVenda = new VendaDao();
		ParcelaDao parcelaDao = new ParcelaDao();
		int indice = 0;
		//CidadeEstadoDao daoCidade = new CidadeEstadoDao();
		/*
		 * for (int i = 1; i <= 10000; i++) { row = plan1.getRow(i);
		 * Iterator<Cell> cellIterator = row.cellIterator(); parcela = new
		 * Parcela(); boolean pularlinha=false; indice++; while
		 * (cellIterator.hasNext()) { Cell cell = cellIterator.next(); if (cell
		 * != null) {
		 * 
		 * 
		 * if(cell.getColumnIndex()==0) {
		 * pedido.setPedido(Integer.parseInt(cell.getStringCellValue())); }
		 * 
		 * else if(cell.getColumnIndex()==1 && (cell.getDateCellValue()!=null )
		 * ) { pedido.setData(cell.getDateCellValue()); }
		 * 
		 * else if(cell.getColumnIndex()==2 && (cell.getStringCellValue()!=null
		 * && !cell.getStringCellValue().isEmpty()) ) {
		 * pedido.setCliente(dao.consultarCodigo(Integer.parseInt(cell.
		 * getStringCellValue()))); }
		 * 
		 * else if(cell.getColumnIndex()==3 && (cell.getStringCellValue()!=null
		 * && !cell.getStringCellValue().isEmpty()) ) {
		 * pedido.setParcelas((Integer.parseInt(cell.getStringCellValue()))); }
		 * 
		 * else if(cell.getColumnIndex()==4 && (cell.getNumericCellValue() !=0 )
		 * ) { pedido.setValor(new BigDecimal(cell.getNumericCellValue())); }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * } }
		 * 
		 * 
		 * }
		 * 
		 * 
		 * 
		 * }
		 */
		for (Venda venda : daoVenda.listar()) {
			Parcela parcela = null;
			Calendar calendar = Calendar.getInstance();
			for (int i = 0; i < venda.getParcelas(); i++) {
				parcela = new Parcela();
				int numeroParcela = (i + 1);
				parcela.setAlias(venda.getPedido() + "/" + numeroParcela);
				BigDecimal big = new BigDecimal(venda.getValor().doubleValue() / venda.getParcelas());
				parcela.setValor(big.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				parcela.setVenda(venda);
				calendar.setTime(venda.getData());
				calendar.add(Calendar.MONTH, 1 + i);
				parcela.setVencimento(calendar.getTime());
				parcelaDao.inserir(parcela);

			}
		}

	}

}
