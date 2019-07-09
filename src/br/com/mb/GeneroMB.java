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

import br.com.entity.Genero;
import br.com.service.GeneroService;

@Named("cadastrarGeneroMB")
@SessionScoped
public class GeneroMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Genero Genero = new Genero();
	private List<Genero> listaGeneros = null;
	
	@Inject
	private GeneroService GeneroService;

	private EntityManager em;
	private EntityManagerFactory managerfactory;
	
	public GeneroMB() {
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
	
	
	public Genero getGenero() {
		return Genero;
	}
	public void setGenero(Genero Genero) {
		this.Genero = Genero;
	}
	

	public List<Genero> getListaGeneros() {
		return listaGeneros;
	}

	public void setListaGeneros(List<Genero> listaGeneros) {
		this.listaGeneros = listaGeneros;
	}
	
	private void atualizarListaGeneros(){
		listaGeneros = GeneroService.listaGeneros(em);
	}

	public String cadastrar() {
		atualizarListaGeneros();
		return "cadastrarGenero";
	}
	
	public String efetuarCadastro() throws NotSupportedException, SystemException {
		GeneroService.gravar(Genero, em);
		
		Genero = null;
		Genero = new Genero();
		
		atualizarListaGeneros();		
		return "cadastrarGenero";
    }
	
	public String excluirGenero(int id) throws NotSupportedException, SystemException {
		GeneroService.excluir(id, em);
		atualizarListaGeneros();
		return "cadastrarGenero";
    }
}