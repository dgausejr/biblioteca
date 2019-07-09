package br.com.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import br.com.entity.Aluno;
@Stateless
public class AlunoRepository {
	
	public void gravar(Aluno aluno, EntityManager em){
		em.getTransaction().begin();
		try {
			if (aluno.getId() == 0){
				inserir(aluno, em);
			} else {
				alterar(aluno, em);
			}
		
			em.getTransaction().commit();
			
		} catch (Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	
	private void inserir(Aluno aluno, EntityManager em){
		List<Aluno> listaGravados = listarAlunos(em);
		int proximo = 0;
		if (listaGravados == null || listaGravados.size() == 0){
			proximo = 1;	
		} else {
			proximo = listaGravados.get(listaGravados.size()-1).getId() + 1;	
		}
		aluno.setId(proximo);
		em.persist(aluno);
		em.flush();
	}

	private void alterar(Aluno aluno, EntityManager em){
		em.merge(aluno);
		em.flush();
	}

	private Aluno findAluno(int id, EntityManager em){
		return em.find(Aluno.class, id);
	}
	
	public void excluir(int id, EntityManager em){
		Aluno aluno = findAluno(id, em);
		em.getTransaction().begin();
		try {
			em.remove(aluno);
			em.flush();
			
			em.getTransaction().commit();
			
		} catch (Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
		}
	}
	
	public Aluno findById(int id, EntityManager em){
		List<Aluno> lista = em.createQuery("Select a from Aluno a where a.id = :id ", Aluno.class).
					setParameter("id", id).getResultList();
		if (lista != null && lista.size() > 0){
			return lista.get(lista.size()-1);
		} else {
			return null;
		}
	}
	
	public List<Aluno> listarAlunos(EntityManager em){
		List<Aluno> lista = em.createQuery("Select e from Aluno e ", Aluno.class).getResultList();
		em.clear();
		return lista;
	}
}
