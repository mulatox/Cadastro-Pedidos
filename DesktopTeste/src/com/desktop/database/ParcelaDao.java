package com.desktop.database;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.desktop.model.Parcela;
import com.desktop.model.Venda;

public class ParcelaDao extends Base_Dao {

	public ArrayList<Parcela> listar() {
		EntityManager gerente = getGerenciadorEntidade();
		Query query = gerente.createQuery("FROM " + Parcela.class.getName());
		return (ArrayList<Parcela>) query.getResultList();
	}

	public ArrayList<Parcela> listar(int codigoVenda) {
		EntityManager gerente = getGerenciadorEntidade();
		Query query = gerente
				.createQuery("FROM " + Parcela.class.getName() + " p where p.venda.codigo=" + codigoVenda);
		return (ArrayList<Parcela>) query.getResultList();
	}
	
	public ArrayList<Parcela> listarAtivas(int codigoVenda) {
		EntityManager gerente = getGerenciadorEntidade();
		Query query = gerente
				.createQuery("FROM " + Parcela.class.getName() + " p where p.status>=0 and p.venda.codigo=" + codigoVenda);
		return (ArrayList<Parcela>) query.getResultList();
	}
	
	public ArrayList<Parcela> listarAtivasPedido(int pedido) {
		EntityManager gerente = getGerenciadorEntidade();
		Query query = gerente
				.createQuery("FROM " + Parcela.class.getName() + " p where p.status>=0 and p.venda.pedido=" + pedido);
		return (ArrayList<Parcela>) query.getResultList();
	}

	public ArrayList<Parcela> listarAtivas() {
		EntityManager gerente = getGerenciadorEntidade();
		Query query = gerente.createQuery("FROM " + Parcela.class.getName() + " p where p.status>=0");
		return (ArrayList<Parcela>) query.getResultList();
	}
	
	public ArrayList<Parcela> listarAtivasOrdenada() {
		EntityManager gerente = getGerenciadorEntidade();
		Query query = gerente.createQuery("FROM " + Parcela.class.getName() + " p where p.status>=0 ");
		return (ArrayList<Parcela>) query.getResultList();
	}


}
