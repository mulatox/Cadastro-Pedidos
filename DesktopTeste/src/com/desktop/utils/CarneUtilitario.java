package com.desktop.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.desktop.database.ParcelaDao;
import com.desktop.model.Parcela;

public class CarneUtilitario {

	public static void main(String args[])
	{
		  try {
		      FileReader arq = new FileReader("./DesktopTeste/relatorios/carnet.txt");
		      BufferedReader lerArq = new BufferedReader(arq);
		 
		      String modeloCarne = lerArq.readLine(); 
		      while (modeloCarne != null) {
		        System.out.printf("%s\n", modeloCarne);
		 
		        modeloCarne = lerArq.readLine(); 
		      }
		 
		      ParcelaDao dao = new ParcelaDao();
		      int indc = 1;
		      for(Parcela parcela:dao.listarAtivas())
		      {
		    	  modeloCarne=modeloCarne
		    	  .replace("#NOME", parcela.getVenda().getCliente().getNome())
		    	  .replace("#ENDERECO", parcela.getVenda().getCliente().getEndereco())
		    	  .replace("#BAIRRO", parcela.getVenda().getCliente().getBairro())
		    	  .replace("#CIDADE", parcela.getVenda().getCliente().getCidade_estado().getCidade())
		    	  .replace("#PEDIDO", ""+parcela.getVenda().getPedido())
		    	  .replace("#N", indc+"")
		    	  .replace("#VENC",""+ parcela.getVencimento())
		    	  .replace("#PARC", ""+parcela.getValor())
		    	  .replace("#DATAVE", ""+new SimpleDateFormat("dd/MM/yyyy").format(parcela.getVenda().getData()));
		    	  indc++;
		    	  
		      }
		      
		      System.out.println(modeloCarne);
		      
		      arq.close();
		    } catch (IOException e) {
		        System.err.printf("Erro na abertura do arquivo: %s.\n",
		          e.getMessage());
		    }
		  
		  
		 
	}
}
