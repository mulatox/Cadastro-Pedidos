package com.desktop.database;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.desktop.model.Parcela;

public class ParcelaDao extends Base_Dao {

	public ArrayList<Parcela> listar() {
		Query query = getGerenciadorEntidade().createQuery("FROM " + Parcela.class.getName());
		return (ArrayList<Parcela>) query.getResultList();
	}

	public ArrayList<Parcela> listar(int codigoVenda) {
		Query query = getGerenciadorEntidade()
				.createQuery("FROM " + Parcela.class.getName() + " p where p.venda.codigo=" + codigoVenda);
		return (ArrayList<Parcela>) query.getResultList();
	}
	
	public ArrayList<Parcela> listarAtivas(int codigoVenda) {
		Query query = getGerenciadorEntidade()
				.createQuery("FROM " + Parcela.class.getName() + " p where p.status>=0 and p.venda.codigo=" + codigoVenda);
		return (ArrayList<Parcela>) query.getResultList();
	}

	public ArrayList<Parcela> listarAtivas() {
		Query query = getGerenciadorEntidade().createQuery("FROM " + Parcela.class.getName() + " p where p.status>=0");
		return (ArrayList<Parcela>) query.getResultList();
	}

	public void removerParcelas(int codigoVenda) {
		EntityManager gerente = getGerenciadorEntidade();
		try {
			gerente.getTransaction().begin();
			Query query = getGerenciadorEntidade()
					.createQuery("delete FROM " + Parcela.class.getName() + " p where p.venda.codigo=" + codigoVenda);
			query.executeUpdate();
		} catch (Exception e) {
			System.out.println("Deu erro!" + e);
			gerente.getTransaction().rollback(); // desfaz transacao se ocorrer
													// erro ao persitir
		} finally {
			if (gerente.getTransaction().isActive()) {
				gerente.getTransaction().commit();
			}

			gerente.close();
		}
	}

}
