package br.com.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import br.com.entity.Autor;
@Stateless
public class AutorRepository {
	
	public void gravar(Autor autor, EntityManager em){
		em.getTransaction().begin();
		try {
			if (autor.getId() == 0){
				inserir(autor, em);
			} else {
				alterar(autor, em);
			}
		
			em.getTransaction().commit();
			
		} catch (Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	
	private void inserir(Autor autor, EntityManager em){
		List<Autor> listaGravados = listarAutores(em);
		int proximo = 0;
		if (listaGravados == null || listaGravados.size() == 0){
			proximo = 1;	
		} else {
			proximo = listaGravados.get(listaGravados.size()-1).getId() + 1;	
		}
		autor.setId(proximo);
		em.persist(autor);
		em.flush();
	}

	private void alterar(Autor autor, EntityManager em){
		em.merge(autor);
		em.flush();
	}

	private Autor findAutor(int id, EntityManager em){
		return em.find(Autor.class, id);
	}
	
	public void excluir(int id, EntityManager em){
		Autor autor = findAutor(id, em);
		em.getTransaction().begin();
		try {
			em.remove(autor);
			em.flush();
			
			em.getTransaction().commit();
			
		} catch (Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
		}
	}
	
	public Autor findById(int id, EntityManager em){
		List<Autor> lista = em.createQuery("Select a from Autor a where a.id = :id ", Autor.class).
					setParameter("id", id).getResultList();
		if (lista != null && lista.size() > 0){
			return lista.get(lista.size()-1);
		} else {
			return null;
		}
	}
	
	public List<Autor> listarAutores(EntityManager em){
		List<Autor> lista = em.createQuery("Select e from Autor e ", Autor.class).getResultList();
		em.clear();
		return lista;
	}
}
