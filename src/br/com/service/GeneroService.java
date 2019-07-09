package br.com.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.entity.Genero;
import br.com.repository.GeneroRepository;

@Stateless
public class GeneroService {
	@Inject
	private GeneroRepository generoRepository;
	
	public void gravar(Genero genero, EntityManager em){
		generoRepository.gravar(genero, em);
	}

	public void excluir(int id, EntityManager em){
		generoRepository.excluir(id, em);
	}
	
	public Genero findById(int id, EntityManager em){
		return generoRepository.findById(id, em);
	}
	
	public List<Genero> listaGeneros(EntityManager em){
		return generoRepository.listarGeneros(em);
	}
}
