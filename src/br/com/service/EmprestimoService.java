package br.com.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.entity.Emprestimo;
import br.com.repository.EmprestimoRepository;

@Stateless
public class EmprestimoService {
	@Inject
	private EmprestimoRepository emprestimoRepository;
	
	public void gravar(Emprestimo emprestimo, EntityManager em){
		emprestimoRepository.gravar(emprestimo, em);
	}

	public void excluir(int id, EntityManager em){
		emprestimoRepository.excluir(id, em);
	}
	
	public Emprestimo findById(int id, EntityManager em){
		return emprestimoRepository.findById(id, em);
	}
	
	public List<Emprestimo> listaEmprestimos(EntityManager em){
		return emprestimoRepository.listaEmprestimos(em);
	}
}
