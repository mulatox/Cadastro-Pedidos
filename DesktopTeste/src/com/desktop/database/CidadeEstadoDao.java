package com.desktop.database;

import java.util.ArrayList;

import javax.persistence.Query;

import com.desktop.model.CidadeEstado;
import com.desktop.model.Cliente;

public class CidadeEstadoDao extends Base_Dao {

	
	public ArrayList<CidadeEstado> listar()
	{
		Query query = getGerenciadorEntidade().createQuery("FROM " + CidadeEstado.class.getName());
		return (ArrayList<CidadeEstado>) query.getResultList();
	}
	
	public CidadeEstado consultar(String nome)
	{
		Query query = getGerenciadorEntidade().createQuery("FROM " + CidadeEstado.class.getName()+" c where c.cidade = :nomeParametro");
		query.setParameter("nomeParametro", nome);
		return (CidadeEstado) query.getSingleResult();
	}
	
	public CidadeEstado consultarCodigo(int codigo) {
		return getGerenciadorEntidade().find(CidadeEstado.class, codigo);
	}
	
}
