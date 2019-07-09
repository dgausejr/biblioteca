package br.com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Emprestimo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	@Temporal(value = TemporalType.DATE)
	private Date data_emp;
	@Temporal(value = TemporalType.DATE)
	private Date data_prev_dev;
	private int id_livro;
	private int id_aluno;
	private int id_funcionario;	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getData_emp() {
		return data_emp;
	}
	public void setData_emp(Date data_emp) {
		this.data_emp = data_emp;
	}
	public Date getData_prev_dev() {
		return data_prev_dev;
	}
	public void setData_prev_dev(Date data_prev_dev) {
		this.data_prev_dev = data_prev_dev;
	}
	public int getId_livro() {
		return id_livro;
	}
	public void setId_livro(int id_livro) {
		this.id_livro = id_livro;
	}
	public int getId_aluno() {
		return id_aluno;
	}
	public void setId_aluno(int id_aluno) {
		this.id_aluno = id_aluno;
	}
	public int getId_funcionario() {
		return id_funcionario;
	}
	public void setId_funcionario(int id_funcionario) {
		this.id_funcionario = id_funcionario;
	}
	
	
}