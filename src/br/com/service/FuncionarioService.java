package br.com.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.entity.Funcionario;
import br.com.repository.FuncionarioRepository;

@Stateless
public class FuncionarioService {
	@Inject
	private FuncionarioRepository funcionarioRepository;
	
	public void gravar(Funcionario funcionario, EntityManager em){
		funcionarioRepository.gravar(funcionario, em);
	}

	public void excluir(int id, EntityManager em){
		funcionarioRepository.excluir(id, em);
	}
	
	public Funcionario findById(int id, EntityManager em){
		return funcionarioRepository.findById(id, em);
	}
	
	public List<Funcionario> listaFuncionarios(EntityManager em){
		return funcionarioRepository.listarFuncionarios(em);
	}
}
