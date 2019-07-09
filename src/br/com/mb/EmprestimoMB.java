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

import br.com.entity.Funcionario;
import br.com.entity.Aluno;
import br.com.entity.Livro;
import br.com.entity.Emprestimo; 
import br.com.service.FuncionarioService;
import br.com.service.AlunoService;
import br.com.service.LivroService;
import br.com.service.EmprestimoService;


@Named("cadastrarEmprestimoMB")
@SessionScoped
public class EmprestimoMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Emprestimo emprestimo = new Emprestimo();
	
	private List<Emprestimo> listaEmprestimos = null;
	
	@Inject
	private EmprestimoService emprestimoService;
	@Inject
	private LivroService livroService;
	@Inject
	private FuncionarioService funcionarioService;
	@Inject
	private AlunoService alunoService;

	private EntityManager em;
	private EntityManagerFactory managerfactory;
	
	public EmprestimoMB() {
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
	
	
	public Emprestimo getEmprestimo() {
		return emprestimo;
	}
	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}
	

	public List<Emprestimo> getListaEmprestimos() {
		return listaEmprestimos;
	}

	public void setListaEmprestimos(List<Emprestimo> listaEmprestimos) {
		this.listaEmprestimos = listaEmprestimos;
	}
	
	private void atualizarListaEmprestimos(){
		listaEmprestimos = emprestimoService.listaEmprestimos(em);
	}

	public String cadastrar() {
		atualizarListaEmprestimos();
		return "cadastrarEmprestimo";
	}
	
	public String efetuarCadastro() throws NotSupportedException, SystemException {
		emprestimoService.gravar(emprestimo, em);
		
		emprestimo = null;
		emprestimo = new Emprestimo();
		
		atualizarListaEmprestimos();		
		return "cadastrarEmprestimo";
    }
	
	public List<Funcionario> listarFuncionarios() throws NotSupportedException, SystemException {
		return funcionarioService.listaFuncionarios(em);
    }
	
	public List<Aluno> listarAlunos() throws NotSupportedException, SystemException {
		return alunoService.listarAlunos(em);
    }
	
	public List<Livro> listarLivros() throws NotSupportedException, SystemException {
		return livroService.listaLivros(em);
    }
	
	public List<Emprestimo> listarEmprestimos() throws NotSupportedException, SystemException {
		return emprestimoService.listaEmprestimos(em);
    }
	
	public String excluirEmprestimo(int id) throws NotSupportedException, SystemException {
		emprestimoService.excluir(id, em);
		atualizarListaEmprestimos();
		return "cadastrarEmprestimo";
    }
	
}