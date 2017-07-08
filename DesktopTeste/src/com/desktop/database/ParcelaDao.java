package com.desktop.database;

import java.util.ArrayList;

import javax.persistence.Query;

import com.desktop.model.Parcela;

public class ParcelaDao extends Base_Dao {
	
	public ArrayList<Parcela> listar()
	{
		Query query = getGerenciadorEntidade().createQuery("FROM " + Parcela.class.getName());
		return (ArrayList<Parcela>) query.getResultList();
	}
	
	public ArrayList<Parcela> listar(int codigoVenda)
	{
		Query query = getGerenciadorEntidade().createQuery("FROM " + Parcela.class.getName()+" p where p.venda="+codigoVenda);
		return (ArrayList<Parcela>) query.getResultList();
	}
	
	
	public ArrayList<Parcela> listarAtivas()
	{
		Query query = getGerenciadorEntidade().createQuery("FROM " + Parcela.class.getName()+" p where p.status=0");
		return (ArrayList<Parcela>) query.getResultList();
	}

}
