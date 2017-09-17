package com.desktop.database;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;

import com.desktop.model.Cliente;


public class Base_Dao {

	final static Logger LOGGER = Logger.getLogger(Base_Dao.class);
	public Object inserir(Object objeto) {
		
		try{
			EntityManager gerente = getGerenciadorEntidade();

			gerente.getTransaction().begin();

			gerente.persist(objeto);
			
			gerente.flush();
			
			gerente.getTransaction().commit();
			
			gerente.close();
			
		}
		
		catch(Exception e)
		{
			LOGGER.error("Erro em Inserir",e);
		}
		
		return objeto;
	}

	public void remover(Object objeto) {
		EntityManager gerente = getGerenciadorEntidade();
		try {
			gerente.getTransaction().begin();

			gerente.remove(gerente.contains(objeto) ? objeto : gerente.merge(objeto));

			gerente.flush();

			gerente.getTransaction().commit();

			gerente.close();
		} catch (Exception e) {
			LOGGER.error("Erro em Remover", e);
		}

	}

	public Object atualizar(Object objeto) {
		try{
			
		EntityManager gerente = getGerenciadorEntidade();

		gerente.getTransaction().begin();

		gerente.merge(objeto);
		
		gerente.flush();
		
		gerente.getTransaction().commit();
		
		gerente.close();
		
		} catch(Exception e) {
			LOGGER.error("Erro em Atualizar",e);
		}
		return objeto;
	}

	
	


	public EntityManager getGerenciadorEntidade() {
		EntityManager gerenciadorEntidade = null;
		try {
			EntityManagerFactory fabrica = null;

			fabrica = FabricaConexao.gerarConexao();

			gerenciadorEntidade = fabrica.createEntityManager();
		} catch (Exception e) {
			LOGGER.error("Erro no gerenciador Entidade", e);
		}
		return gerenciadorEntidade;

	}

}
