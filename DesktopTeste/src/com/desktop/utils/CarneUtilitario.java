package com.desktop.utils;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JOptionPane;

import com.desktop.database.ParcelaDao;
import com.desktop.model.Parcela;

public class CarneUtilitario {
	
	private static final String DESKTOP_TESTE_RELATORIOS_TEMP_TXT = "./DesktopTeste/relatorios/temp.txt";
	private static final String DESKTOP_TESTE_RELATORIOS_CARNET_TXT = "./DesktopTeste/relatorios/carnet.txt";

	public static void imprimirCarne(int pedido)
	{
		try {
			FileReader arq = new FileReader(DESKTOP_TESTE_RELATORIOS_CARNET_TXT);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine() + "\n";
			String modeloCarne = linha;
			while (linha != null) {
				System.out.printf("%s\n", modeloCarne);
				linha = lerArq.readLine();
				if (linha != null) {
					modeloCarne += linha + "\n";
					;
				}

			}

			ParcelaDao dao = new ParcelaDao();
			int indc = 1;
			String resultadoCarne = "";
			for (Parcela parcela : dao.listarAtivas(pedido)) {
				resultadoCarne += modeloCarne.replace("#NOME", parcela.getVenda().getCliente().getNome())
						.replace("#ENDERECO", parcela.getVenda().getCliente().getEndereco())
						.replace("#BAIRRO", parcela.getVenda().getCliente().getBairro())
						.replace("#CIDADE", parcela.getVenda().getCliente().getCidade_estado().getCidade())
						.replace("#PEDIDO", "" + parcela.getVenda().getPedido()).replace("#N", indc + "")
						.replace("#VENC", "" + new SimpleDateFormat("dd/MM/yyyy").format(parcela.getVencimento()))
						.replace("#PARC", "" + parcela.getValor()).replace("#DATAVE",
								"" + new SimpleDateFormat("dd/MM/yyyy").format(parcela.getVenda().getData()))
						+ "\n";
				;
				indc++;
			}
			FileWriter arqWriter = new FileWriter(DESKTOP_TESTE_RELATORIOS_TEMP_TXT);
			PrintWriter gravarArq = new PrintWriter(arqWriter);
			gravarArq.print(resultadoCarne);
			gravarArq.close();
			System.out.println(resultadoCarne);
			Desktop.getDesktop().print(new File(DESKTOP_TESTE_RELATORIOS_TEMP_TXT));
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}
	}

	public static void main(String args[]) {
		try {
			FileReader arq = new FileReader(DESKTOP_TESTE_RELATORIOS_CARNET_TXT);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine() + "\n";
			String modeloCarne = linha;
			while (linha != null) {
				System.out.printf("%s\n", modeloCarne);
				linha = lerArq.readLine();
				if (linha != null) {
					modeloCarne += linha + "\n";
					;
				}

			}

			ParcelaDao dao = new ParcelaDao();
			int indc = 1;
			String resultadoCarne = "";
			for (Parcela parcela : dao.listarAtivas()) {
				resultadoCarne += modeloCarne.replace("#NOME", parcela.getVenda().getCliente().getNome())
						.replace("#ENDERECO", parcela.getVenda().getCliente().getEndereco())
						.replace("#BAIRRO", parcela.getVenda().getCliente().getBairro())
						.replace("#CIDADE", parcela.getVenda().getCliente().getCidade_estado().getCidade())
						.replace("#PEDIDO", "" + parcela.getVenda().getPedido()).replace("#N", indc + "")
						.replace("#VENC", "" + new SimpleDateFormat("dd/MM/yyyy").format(parcela.getVencimento()))
						.replace("#PARC", "" + parcela.getValor()).replace("#DATAVE",
								"" + new SimpleDateFormat("dd/MM/yyyy").format(parcela.getVenda().getData()))
						+ "\n";
				;
				indc++;
			}
			FileWriter arqWriter = new FileWriter(DESKTOP_TESTE_RELATORIOS_TEMP_TXT);
			PrintWriter gravarArq = new PrintWriter(arqWriter);
			gravarArq.print(resultadoCarne);
			gravarArq.close();
			System.out.println(resultadoCarne);
			Desktop.getDesktop().print(new File(DESKTOP_TESTE_RELATORIOS_TEMP_TXT));
			/*PrintService impressoraPadrao = PrintServiceLookup.lookupDefaultPrintService();
		    System.out.println("A  impressora padrao é " + impressoraPadrao.getName());
		    DocFlavor docFlavor =  DocFlavor.INPUT_STREAM.AUTOSENSE;
		    HashDocAttributeSet hashDocAttributeSet= new HashDocAttributeSet();
		    try {         
		          FileInputStream fileInputStream = new FileInputStream("./DesktopTeste/relatorios/temp.txt");
		          Doc doc = new SimpleDoc(fileInputStream, docFlavor, hashDocAttributeSet);
		          PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
		          PrintService printServico = PrintServiceLookup.lookupDefaultPrintService();
		            if (printServico != null) {
		            DocPrintJob docPrintJob = printServico.createPrintJob();
		                //madar imprimir documento
		            try{                   
		                    docPrintJob.print(doc, printRequestAttributeSet);            
		                }  catch(PrintException ex){
		                    JOptionPane.showMessageDialog(null,ex);
		                }                
		        }
		        }   catch (FileNotFoundException ex) {
		           JOptionPane.showMessageDialog(null,ex);
		        }*/

			//
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}

	}
}
