package br.com.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import br.com.entity.Emprestimo;
@Stateless
public class EmprestimoRepository {
	
	public void gravar(Emprestimo emprestimo, EntityManager em){
		em.getTransaction().begin();
		try {
			if (emprestimo.getId() == 0){
				inserir(emprestimo, em);
			} else {
				alterar(emprestimo, em);
			}
		
			em.getTransaction().commit();
			
		} catch (Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	
	private void inserir(Emprestimo emprestimo, EntityManager em){
		List<Emprestimo> listaGravados = listaEmprestimos(em);
		int proximo = 0;
		if (listaGravados == null || listaGravados.size() == 0){
			proximo = 1;	
		} else {
			proximo = listaGravados.get(listaGravados.size()-1).getId() + 1;	
		}
		emprestimo.setId(proximo);
		em.persist(emprestimo);
		em.flush();
	}

	private void alterar(Emprestimo emprestimo, EntityManager em){
		em.merge(emprestimo);
		em.flush();
	}

	private Emprestimo findLivro(int id, EntityManager em){
		return em.find(Emprestimo.class, id);
	}
	
	public void excluir(int id, EntityManager em){
		Emprestimo emprestimo = findLivro(id, em);
		em.getTransaction().begin();
		try {
			em.remove(emprestimo);
			em.flush();
			
			em.getTransaction().commit();
			
		} catch (Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
		}
	}
	
	public Emprestimo findById(int id, EntityManager em){
		List<Emprestimo> lista = em.createQuery("Select e from Emprestimo e where e.id = :id ", Emprestimo.class).
					setParameter("id", id).getResultList();
		if (lista != null && lista.size() > 0){
			return lista.get(lista.size()-1);
		} else {
			return null;
		}
	}
	
	public List<Emprestimo> listaEmprestimos(EntityManager em){
		List<Emprestimo> lista = em.createQuery("Select f from Emprestimo f ", Emprestimo.class).getResultList();
		em.clear();
		return lista;
	}
}