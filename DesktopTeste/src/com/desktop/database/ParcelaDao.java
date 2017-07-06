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

}
