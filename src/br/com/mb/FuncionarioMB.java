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
import br.com.service.FuncionarioService;

@Named("cadastrarFuncionarioMB")
@SessionScoped
public class FuncionarioMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Funcionario Funcionario = new Funcionario();
	private List<Funcionario> listaFuncionarios = null;
	
	@Inject
	private FuncionarioService FuncionarioService;

	private EntityManager em;
	private EntityManagerFactory managerfactory;
	
	public FuncionarioMB() {
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
	
	
	public Funcionario getFuncionario() {
		return Funcionario;
	}
	public void setFuncionario(Funcionario Funcionario) {
		this.Funcionario = Funcionario;
	}
	

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}
	
	private void atualizarListaFuncionarios(){
		listaFuncionarios = FuncionarioService.listaFuncionarios(em);
	}

	public String cadastrar() {
		atualizarListaFuncionarios();
		return "cadastrarFuncionario";
	}
	
	public String efetuarCadastro() throws NotSupportedException, SystemException {
		FuncionarioService.gravar(Funcionario, em);
		
		Funcionario = null;
		Funcionario = new Funcionario();
		
		atualizarListaFuncionarios();		
		return "cadastrarFuncionario";
    }
	
	public String excluirFuncionario(int id) throws NotSupportedException, SystemException {
		FuncionarioService.excluir(id, em);
		atualizarListaFuncionarios();
		return "cadastrarFuncionario";
    }
}