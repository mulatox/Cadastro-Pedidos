package com.desktop.database;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.desktop.model.Cliente;


public class Base_Dao {


	public void inserir(Object objeto) {

		EntityManager gerente = getGerenciadorEntidade();

		gerente.getTransaction().begin();

		gerente.persist(objeto);
		
		gerente.flush();
		
		gerente.getTransaction().commit();
		
		gerente.close();
	}

	public void remover(Object objeto) {
		EntityManager gerente = getGerenciadorEntidade();

		gerente.getTransaction().begin();
		
		gerente.remove(objeto);
		
		gerente.flush();
		
		gerente.getTransaction().commit();
		
		gerente.close();
	}

	public void atualizar(Object objeto) {
		EntityManager gerente = getGerenciadorEntidade();

		gerente.getTransaction().begin();

		gerente.merge(objeto);
		
		gerente.flush();
		
		gerente.getTransaction().commit();
		
		gerente.close();
	}

	public Object consultarCodigo(int codigo) {
		return getGerenciadorEntidade().find(this.getClass(), codigo);
	}
	


	public EntityManager getGerenciadorEntidade() {
		EntityManager gerenciadorEntidade = null;

		EntityManagerFactory fabrica = null;

		fabrica = FabricaConexao.gerarConexao();

		gerenciadorEntidade = fabrica.createEntityManager();

		return gerenciadorEntidade;

	}

}