package com.desktop.database;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.desktop.model.Cliente;
import com.desktop.model.Venda;

public class VendaDao extends Base_Dao {

	public ArrayList<Venda> listar() {
		Query query = getGerenciadorEntidade().createQuery("FROM " + Venda.class.getName() + " v where v.status!=-1");
		ArrayList<Venda> vendas = (ArrayList<Venda>) query.getResultList();
		return vendas;

	}

	public Venda consultarCodigo(int codigo) {
		return getGerenciadorEntidade().find(Venda.class, codigo);
	}


}
