package br.com.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.entity.Usuario;
import br.com.repository.UsuarioRepository;

@Stateless
public class UsuarioService {
	@Inject
	private UsuarioRepository usuRepository;
	
	public Usuario validarUsuario(int codigo, String senha, EntityManager em){
		return usuRepository.validarUsuario(codigo, senha, em);
	}
	
	public List<Usuario> listarUsuarios(EntityManager em){
		return usuRepository.listarUsuarios(em);
	}
}
