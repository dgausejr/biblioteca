package br.com.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import br.com.entity.Livro;
@Stateless
public class LivroRepository {
	
	public void gravar(Livro livro, EntityManager em){
		em.getTransaction().begin();
		try {
			if (livro.getId() == 0){
				inserir(livro, em);
			} else {
				alterar(livro, em);
			}
		
			em.getTransaction().commit();
			
		} catch (Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	
	private void inserir(Livro livro, EntityManager em){
		List<Livro> listaGravados = listaLivros(em);
		int proximo = 0;
		if (listaGravados == null || listaGravados.size() == 0){
			proximo = 1;	
		} else {
			proximo = listaGravados.get(listaGravados.size()-1).getId() + 1;	
		}
		livro.setId(proximo);
		em.persist(livro);
		em.flush();
	}

	private void alterar(Livro livro, EntityManager em){
		em.merge(livro);
		em.flush();
	}

	private Livro findLivro(int id, EntityManager em){
		return em.find(Livro.class, id);
	}
	
	public void excluir(int id, EntityManager em){
		Livro livro = findLivro(id, em);
		em.getTransaction().begin();
		try {
			em.remove(livro);
			em.flush();
			
			em.getTransaction().commit();
			
		} catch (Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
		}
	}
	
	public Livro findById(int id, EntityManager em){
		List<Livro> lista = em.createQuery("Select l from Livro l where l.id = :id ", Livro.class).
					setParameter("id", id).getResultList();
		if (lista != null && lista.size() > 0){
			return lista.get(lista.size()-1);
		} else {
			return null;
		}
	}
	
	public List<Livro> listaLivros(EntityManager em){
		List<Livro> lista = em.createQuery("Select m from Livro m ", Livro.class).getResultList();
		em.clear();
		return lista;
	}
}