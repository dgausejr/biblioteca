package br.com.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Livro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private String titulo;
	private String isbn;
	private int qtde_disp;
	private Date data_pub;	
	private int id_genero;
	private int id_autor;
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getQtde_disp() {
		return qtde_disp;
	}
	public void setQtde_disp(int qtde_disp) {
		this.qtde_disp = qtde_disp;
	}
	public Date getData_pub() {
		return data_pub;
	}
	public void setData_pub(Date data_pub) {
		this.data_pub = data_pub;
	}
	public int getId_genero() {
		return id_genero;
	}
	public void setId_genero(int id_genero) {
		this.id_genero = id_genero;
	}
	public int getId_autor() {
		return id_autor;
	}
	public void setId_autor(int id_autor) {
		this.id_autor = id_autor;
	}
		
}