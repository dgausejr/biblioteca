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

import br.com.entity.Aluno;
import br.com.service.AlunoService;

@Named("cadastrarAlunoMB")
@SessionScoped
public class AlunoMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Aluno aluno = new Aluno();
	private List<Aluno> listaAlunos = null;
	
	@Inject
	private AlunoService alunoService;

	private EntityManager em;
	private EntityManagerFactory managerfactory;
	
	public AlunoMB() {
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
	
	
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	

	public List<Aluno> getListaAlunos() {
		return listaAlunos;
	}

	public void setListaAlunos(List<Aluno> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}
	
	private void atualizarListaAlunos(){
		listaAlunos = alunoService.listarAlunos(em);
	}

	public String cadastrar() {
		atualizarListaAlunos();
		return "cadastrarAluno";
	}
	
	public String efetuarCadastro() throws NotSupportedException, SystemException {
		alunoService.gravar(aluno, em);
		
		aluno = null;
		aluno = new Aluno();
		
		atualizarListaAlunos();		
		return "cadastrarAluno";
    }
	
	public String excluirAluno(int id) throws NotSupportedException, SystemException {
		alunoService.excluir(id, em);
		atualizarListaAlunos();
		return "cadastrarAluno";
    }
}
