package br.com.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Genero implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private String descricao;
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setNome(String descricao) {
		this.descricao = descricao;
	}
		
}