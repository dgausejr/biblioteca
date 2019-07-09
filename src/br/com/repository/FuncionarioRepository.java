package br.com.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import br.com.entity.Funcionario;
@Stateless
public class FuncionarioRepository {
	
	public void gravar(Funcionario funcionario, EntityManager em){
		em.getTransaction().begin();
		try {
			if (funcionario.getId() == 0){
				inserir(funcionario, em);
			} else {
				alterar(funcionario, em);
			}
		
			em.getTransaction().commit();
			
		} catch (Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	
	private void inserir(Funcionario funcionario, EntityManager em){
		List<Funcionario> listaGravados = listarFuncionarios(em);
		int proximo = 0;
		if (listaGravados == null || listaGravados.size() == 0){
			proximo = 1;	
		} else {
			proximo = listaGravados.get(listaGravados.size()-1).getId() + 1;	
		}
		funcionario.setId(proximo);
		em.persist(funcionario);
		em.flush();
	}

	private void alterar(Funcionario funcionario, EntityManager em){
		em.merge(funcionario);
		em.flush();
	}

	private Funcionario findFuncionario(int id, EntityManager em){
		return em.find(Funcionario.class, id);
	}
	
	public void excluir(int id, EntityManager em){
		Funcionario funcionario = findFuncionario(id, em);
		em.getTransaction().begin();
		try {
			em.remove(funcionario);
			em.flush();
			
			em.getTransaction().commit();
			
		} catch (Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
		}
	}
	
	public Funcionario findById(int id, EntityManager em){
		List<Funcionario> lista = em.createQuery("Select f from Funcionario f where f.id = :id ", Funcionario.class).
					setParameter("id", id).getResultList();
		if (lista != null && lista.size() > 0){
			return lista.get(lista.size()-1);
		} else {
			return null;
		}
	}
	
	public List<Funcionario> listarFuncionarios(EntityManager em){
		List<Funcionario> lista = em.createQuery("Select i from Funcionario i ", Funcionario.class).getResultList();
		em.clear();
		return lista;
	}
}