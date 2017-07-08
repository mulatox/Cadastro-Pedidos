package com.desktop.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.towel.el.annotation.Resolvable;

@Entity
public class Venda {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Resolvable(colName="CÓDIGO")
	private int codigo;
	
	@Resolvable(colName="PEDIDO")
	private int pedido;
	
	@Resolvable(colName="VALOR")
	private double valor;
	
	@Resolvable(colName="PARCELAS")
	private int parcelas;
	
	@Resolvable(colName="CLIENTE")
	@ManyToOne
	@JoinColumn(name="cliente")
	private Cliente cliente;
	
	@Resolvable(colName="DATA")
	private Date data;
	
	
	private int status;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}


	public int getParcelas() {
		return parcelas;
	}

	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getPedido() {
		return pedido;
	}

	public void setPedido(int pedido) {
		this.pedido = pedido;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getPedido()+"";
	}
	
	
	

}
