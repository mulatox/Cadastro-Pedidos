package com.desktop.database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FabricaConexao
{

	private static EntityManagerFactory fabrica;

	private FabricaConexao()
	{
		fabrica = Persistence.createEntityManagerFactory("desktop");
	}

	// Retorna uma fabrica de gerenciador de entidades para ser usada pelos
	// DAOs.
	public static synchronized EntityManagerFactory gerarConexao()
	{
		if (fabrica == null || !fabrica.isOpen())
		{
			fabrica = Persistence.createEntityManagerFactory("desktop");
		}
		return fabrica;
	}
	
	public static synchronized void fecharConexao()
	{
		
		if(fabrica!=null)
		{
			fabrica.close();
		}
		
	}

}
