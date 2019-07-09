package br.com.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private String nome;
	private  String celular;
	private Date nasc;
	private String email;
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public Date getNasc() {
		return nasc;
	}
	public void setNasc(Date nasc) {
		this.nasc = nasc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}