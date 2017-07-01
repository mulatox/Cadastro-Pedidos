package com.desktop.model;

import java.util.Date;

public class Parcela {
	
	private int codigo;
	private double valor;
	private int status;
	private Date vencimento;
	private String alias;
	private int venda;
	
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getVencimento() {
		return vencimento;
	}
	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public int getVenda() {
		return venda;
	}
	public void setVenda(int venda) {
		this.venda = venda;
	}
	
	

}
