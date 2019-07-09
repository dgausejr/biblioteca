package br.com.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import br.com.entity.Genero;
@Stateless
public class GeneroRepository {
	
	public void gravar(Genero genero, EntityManager em){
		em.getTransaction().begin();
		try {
			if (genero.getId() == 0){
				inserir(genero, em);
			} else {
				alterar(genero, em);
			}
		
			em.getTransaction().commit();
			
		} catch (Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	
	private void inserir(Genero genero, EntityManager em){
		List<Genero> listaGravados = listarGeneros(em);
		int proximo = 0;
		if (listaGravados == null || listaGravados.size() == 0){
			proximo = 1;	
		} else {
			proximo = listaGravados.get(listaGravados.size()-1).getId() + 1;	
		}
		genero.setId(proximo);
		em.persist(genero);
		em.flush();
	}

	private void alterar(Genero genero, EntityManager em){
		em.merge(genero);
		em.flush();
	}

	private Genero findGenero(int id, EntityManager em){
		return em.find(Genero.class, id);
	}
	
	public void excluir(int id, EntityManager em){
		Genero genero = findGenero(id, em);
		em.getTransaction().begin();
		try {
			em.remove(genero);
			em.flush();
			
			em.getTransaction().commit();
			
		} catch (Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
		}
	}
	
	public Genero findById(int id, EntityManager em){
		List<Genero> lista = em.createQuery("Select g from Genero g where g.id = :id ", Genero.class).
					setParameter("id", id).getResultList();
		if (lista != null && lista.size() > 0){
			return lista.get(lista.size()-1);
		} else {
			return null;
		}
	}
	
	public List<Genero> listarGeneros(EntityManager em){
		List<Genero> lista = em.createQuery("Select h from Genero h ", Genero.class).getResultList();
		em.clear();
		return lista;
	}
}