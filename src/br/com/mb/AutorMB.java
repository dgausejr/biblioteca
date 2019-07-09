package br.com.mb;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

import br.com.entity.Autor;
import br.com.service.AutorService;

@Named("cadastrarAutorMB")
@SessionScoped
public class AutorMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Autor Autor = new Autor();
	private List<Autor> listaAutores = null;
	
	@Inject
	private AutorService AutorService;

	private EntityManager em;
	private EntityManagerFactory managerfactory;
	
	public AutorMB() {
		super();
		managerfactory = Persistence.createEntityManagerFactory("biblioteca");
		em = managerfactory.createEntityManager();
	}
	
	@PreDestroy
	public void destruindoClasse(){
		System.out.println("Destruindo objeto");

		if (em != null){
			em.close();
		}
		em = null;		
		if (managerfactory != null){
			managerfactory.close();
		}
		managerfactory = null;
	}
	
	
	public Autor getAutor() {
		return Autor;
	}
	public void setAutor(Autor Autor) {
		this.Autor = Autor;
	}
	

	public List<Autor> getListaAutores() {
		return listaAutores;
	}

	public void setListaAutores(List<Autor> listaAutores) {
		this.listaAutores = listaAutores;
	}
	
	private void atualizarListaAutores(){
		listaAutores = AutorService.listaAutores(em);
	}

	public String cadastrar() {
		atualizarListaAutores();
		return "cadastrarAutor";
	}
	
	public String efetuarCadastro() throws NotSupportedException, SystemException {
		AutorService.gravar(Autor, em);
		
		Autor = null;
		Autor = new Autor();
		
		atualizarListaAutores();		
		return "cadastrarAutor";
    }
	
	public String excluirAutor(int id) throws NotSupportedException, SystemException {
		AutorService.excluir(id, em);
		atualizarListaAutores();
		return "cadastrarAutor";
    }
}