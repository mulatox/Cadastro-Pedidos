package com.desktop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.towel.el.annotation.Resolvable;

@Entity
@Table(name="Cidade_Estado")
public class CidadeEstado {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Resolvable(colName="CÓDIGO")
	private int codigo;
	
	@Resolvable(colName="CIDADE")
	private String cidade;
	
	@Resolvable(colName="ESTADO")
	private String estado;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	

}
