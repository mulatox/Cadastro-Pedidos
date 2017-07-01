package com.desktop.utils;

import java.util.ArrayList;

import com.desktop.model.Cliente;

public class ClienteMock {

	
	public static ArrayList<Cliente> gerarClientes()
	{
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		Cliente cliente = new Cliente();
		cliente.setNome("Levy Pinheiro Mulato Cagodo Mato");
		cliente.setCodigo(1);
		cliente.setEndereco("Avenida Joao Pessoa Matos");
		cliente.setBairro("Montese");
		cliente.setCpf("014650789");
		cliente.setTelefone("32324219");
		clientes.add(cliente);
		return clientes;
	}
	
}
