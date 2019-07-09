package br.com.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.entity.Livro;
import br.com.repository.LivroRepository;

@Stateless
public class LivroService {
	@Inject
	private LivroRepository livroRepository;
	
	public void gravar(Livro livro, EntityManager em){
		livroRepository.gravar(livro, em);
	}

	public void excluir(int id, EntityManager em){
		livroRepository.excluir(id, em);
	}
	
	public Livro findById(int id, EntityManager em){
		return livroRepository.findById(id, em);
	}
	
	public List<Livro> listaLivros(EntityManager em){
		return livroRepository.listaLivros(em);
	}

}
