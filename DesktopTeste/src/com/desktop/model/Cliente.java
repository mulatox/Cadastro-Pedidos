package com.desktop.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.towel.el.annotation.Resolvable;

@Entity
public class Cliente {
	
	
	
	@Resolvable(colName="nome")
	private String nome;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Resolvable(colName="codigo")
	private int codigo;
	
	@Resolvable(colName="endereco")
	private String endereco;
	
	@Resolvable(colName="bairro")
	private String bairro;
	
	@Resolvable(colName="cpf")
	private String cpf;
	
	@Resolvable(colName="telefone")
	private String telefone;
	
	private String observacao;
	
	private Date dataCadastro;
	
	private Date dataNascimento;
	
	private int status;
	
	private int cidade_estado;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public int getCidade_estado() {
		return cidade_estado;
	}
	public void setCidade_estado(int cidade_estado) {
		this.cidade_estado = cidade_estado;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
	
	

}
