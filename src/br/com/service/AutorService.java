package br.com.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.entity.Autor;
import br.com.repository.AutorRepository;

@Stateless
public class AutorService {
	@Inject
	private AutorRepository autorRepository;
	
	public void gravar(Autor autor, EntityManager em){
		autorRepository.gravar(autor, em);
	}

	public void excluir(int id, EntityManager em){
		autorRepository.excluir(id, em);
	}
	
	public Autor findById(int id, EntityManager em){
		return autorRepository.findById(id, em);
	}
	
	public List<Autor> listaAutores(EntityManager em){
		return autorRepository.listarAutores(em);
	}
}
