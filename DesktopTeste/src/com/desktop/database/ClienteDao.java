package com.desktop.database;

import java.util.ArrayList;

import javax.persistence.Query;

import com.desktop.model.Cliente;
import com.desktop.model.Parcela;

public class ClienteDao extends Base_Dao {

	
	public ArrayList<Cliente> listar()
	{
		Query query = getGerenciadorEntidade().createQuery("FROM " + Cliente.class.getName()+"  c LEFT JOIN FETCH c.cidade_estado where c.status!=-1 order by c.nome ");
		return (ArrayList<Cliente>) query.getResultList();
	}
	
	public Cliente consultar(String nome)
	{
		Query query = getGerenciadorEntidade().createQuery("FROM " + Cliente.class.getName()+" c where c.nome = :nomeParametro");
		query.setParameter("nomeParametro", nome);
		return (Cliente) query.getSingleResult();
	}
	
	public Cliente consultarCodigo(int codigo) {
		return getGerenciadorEntidade().find(Cliente.class, codigo);
	}
	
	public ArrayList<Cliente> listarPendentes()
	{
		ParcelaDao parcelaDAO = new ParcelaDao();
		ArrayList<Parcela> parcelas = parcelaDAO.listarAtivas();
		ArrayList<Cliente> clientes = listar();
		ArrayList<Cliente> clientesPendentes = new ArrayList<Cliente>();
		for(Cliente cliente : clientes){
			for(Parcela parcela : parcelas){
				if(parcela.getVenda().getCliente().getCodigo()==cliente.getCodigo() && parcela.getStatus()==0){
					clientesPendentes.add(cliente);
				}
			}
		}
			
		return clientesPendentes;
		
	}
	
}
