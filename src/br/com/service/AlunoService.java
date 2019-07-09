package br.com.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.entity.Aluno;
import br.com.repository.AlunoRepository;

@Stateless
public class AlunoService {
	@Inject
	private AlunoRepository alunoRepository;
	
	public void gravar(Aluno aluno, EntityManager em){
		alunoRepository.gravar(aluno, em);
	}

	public void excluir(int id, EntityManager em){
		alunoRepository.excluir(id, em);
	}
	
	public Aluno findById(int id, EntityManager em){
		return alunoRepository.findById(id, em);
	}
	
	public List<Aluno> listarAlunos(EntityManager em){
		return alunoRepository.listarAlunos(em);
	}
}
