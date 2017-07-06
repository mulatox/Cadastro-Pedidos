package com.desktop.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.towel.el.annotation.Resolvable;

@Entity
public class Parcela {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Resolvable(colName="CÓDIGO")
	private int codigo;
	
	@Resolvable(colName="VALOR")
	private double valor;
	
	@Resolvable(colName="STATUS")
	private int status;
	
	@Resolvable(colName="VENCIMENTO")
	private Date vencimento;
	
	@Resolvable(colName="ALIAS")
	private String alias;

	@Resolvable(colName="VENDA")
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
