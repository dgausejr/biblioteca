package br.com.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import br.com.entity.Usuario;
@Stateless
public class UsuarioRepository {
	
	private Usuario findUsuario(int codigo, EntityManager em){
		return em.find(Usuario.class, codigo);
	}
	
	public Usuario validarUsuario(int codigo, String senha, EntityManager em){
		List<Usuario> lista = em.createQuery("Select u from Usuario u "
											+ "where u.usuario = :usuario "
											+ "  and u.senha = :senha ", Usuario.class).
					setParameter("usuario", codigo).
					setParameter("senha", senha.trim()).getResultList();
		if (lista != null && lista.size() > 0){
			return lista.get(lista.size()-1);
		} else {
			return null;
		}
	}
	
	public List<Usuario> listarUsuarios(EntityManager em){
		List<Usuario> lista = em.createQuery("Select u from Usuario u ", Usuario.class).getResultList();
		em.clear();
		return lista;
	}
}
