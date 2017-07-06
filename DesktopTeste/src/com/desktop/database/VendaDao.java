package com.desktop.database;

import java.util.ArrayList;

import javax.persistence.Query;

import com.desktop.model.Cliente;
import com.desktop.model.Venda;

public class VendaDao extends Base_Dao {

	
	public ArrayList<Venda> listar()
	{
		Query query = getGerenciadorEntidade().createQuery("FROM " + Venda.class.getName()+" v where v.status!=-1");
		return (ArrayList<Venda>) query.getResultList();
	}
	
}
