package com.desktop.utils;


import java.util.ArrayList;

import com.desktop.database.ClienteDao;
import com.desktop.model.Cliente;
import com.towel.el.FieldResolver;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.event.ObjectSelectListener;
import com.towel.swing.event.SelectEvent;
import com.towel.swing.table.SelectTable;

public class TesteHibernate {

	public static void main (String args[])
	{
		
		/*try{
		ClienteDao dao = new ClienteDao();
		Cliente cliente = new Cliente();
		cliente.setNome("Nomeasasd Teste");
		cliente.setBairro("Nome Teste");
		cliente.setCpf("Nome Testeasdasd");
		cliente.setEmail("Nome Teste");
		cliente.setLogradouro("Nome Teste");
		
		dao.inserir(cliente);
		
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		*/
		ClienteDao dao = new ClienteDao();
		;
		
		FieldResolver[] cols = new AnnotationResolver(Cliente.class)
			    .resolve("nome:Nome");
			SelectTable st =new SelectTable<Cliente>(cols,dao.listar());
			st.addObjectSelectListener(new ObjectSelectListener() {

				public void notifyObjectSelected(SelectEvent selectevent) {
					 Cliente p = (Cliente) selectevent.getObject();
			            System.out.println(p.getNome());					
				}

			});
			st.showSelectTable();




	}
	
}
