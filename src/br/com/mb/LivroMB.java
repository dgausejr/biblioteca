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
import br.com.entity.Autor;
import br.com.entity.Livro;
import br.com.service.GeneroService;
import br.com.service.AutorService;
import br.com.service.LivroService;

@Named("cadastrarLivroMB")
@SessionScoped
public class LivroMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Livro livro = new Livro();
	
	private List<Livro> listaLivros = null;
	
	@Inject
	private LivroService livroService;
	@Inject
	private GeneroService generoService;
	@Inject
	private AutorService autorService;

	private EntityManager em;
	private EntityManagerFactory managerfactory;
	
	public LivroMB() {
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
	
	
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	

	public List<Livro> getListaLivros() {
		return listaLivros;
	}

	public void setListaLivros(List<Livro> listaLivros) {
		this.listaLivros = listaLivros;
	}
	
	private void atualizarListaLivros(){
		listaLivros = livroService.listaLivros(em);
	}

	public String cadastrar() {
		atualizarListaLivros();
		return "cadastrarLivro";
	}
	
	public String efetuarCadastro() throws NotSupportedException, SystemException {
		livroService.gravar(livro, em);
		
		livro = null;
		livro = new Livro();
		
		atualizarListaLivros();		
		return "cadastrarLivro";
    }
	
	public List<Genero> listarGeneros() throws NotSupportedException, SystemException {
		return generoService.listaGeneros(em);
    }
	
	public List<Autor> listarAutores() throws NotSupportedException, SystemException {
		return autorService.listaAutores(em);
    }
	
	public List<Livro> listarLivros() throws NotSupportedException, SystemException {
		return livroService.listaLivros(em);
    }
	
	public String excluirLivro(int id) throws NotSupportedException, SystemException {
		livroService.excluir(id, em);
		atualizarListaLivros();
		return "cadastrarLivros";
    }
	
}