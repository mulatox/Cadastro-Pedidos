package com.desktop.database;

import java.util.ArrayList;

import javax.persistence.Query;

import com.desktop.model.Cliente;

public class ClienteDao extends Base_Dao {

	
	public ArrayList<Cliente> listar()
	{
		Query query = getGerenciadorEntidade().createQuery("FROM " + Cliente.class.getName()+" c where c.status!=-1");
		return (ArrayList<Cliente>) query.getResultList();
	}
	
}
