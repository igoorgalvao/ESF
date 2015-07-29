package br.com.esf.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.arquitetura.bean.PaginableBean;
import br.com.arquitetura.excecao.ExcecaoUtil;
import br.com.arquitetura.service.UniversalManager;
import br.com.arquitetura.util.ConstantesARQ;
import br.com.arquitetura.util.FacesMessagesUtil;
import br.com.esf.entidade.Escola;
import br.com.esf.entidade.Professor;
import br.com.esf.entidade.Turma;
import br.com.esf.util.Constantes;

@ManagedBean(name = "turmaBean")
@ViewScoped
public class TurmaBean extends PaginableBean<Turma> {

	private static final long serialVersionUID = 1L;

	private List<Escola> escolas;
	private Long idEscola;
	private List<Professor> professores;
	private Long idProfessor;

	public TurmaBean() {
		escolas = new ArrayList<Escola>();
		professores = new ArrayList<Professor>();
	}

	@PostConstruct
	public void init() {

		try {
			if (escolas.isEmpty()) {
				Escola escola = new Escola();
				escolas = universalManager.listBy(escola);
				Collections.sort(escolas, new Comparator<Escola>() {
					@Override
					public int compare(Escola o1, Escola o2) {
						return o1.getNome().trim().compareTo(o2.getNome());
					}
				});
			}
			if (professores.isEmpty()) {
				Professor prof = new Professor();
				professores = universalManager.listBy(prof);
				Collections.sort(professores, new Comparator<Professor>() {
					@Override
					public int compare(Professor o1, Professor o2) {
						return o1.getProfessor().trim().compareTo(o2.getProfessor());
					}
				});
			}

		} catch (Exception e) {
			ExcecaoUtil.tratarExcecao(e);

		}

	}

	@Override
	public String load() {
		super.load();
		idEscola = getModel().getEscola().getId();
		idProfessor = getModel().getProfessor().getId();
		return "";
	}

	public void limpar() {
		setModel(createModel());
		idEscola = null;
		idProfessor = null;
	}

	@Override
	public String save() {
		try {

			if (camposInvalido()) {
				return "";
			}

			getModel().setProfessor(new Professor());
			getModel().getProfessor().setId(idProfessor);
			getModel().setEscola(new Escola());
			getModel().getEscola().setId(idEscola);

			super.save();
			limpar();
		} catch (Exception e) {
			ExcecaoUtil.tratarExcecao(e);
		}

		return "";
	}

	private boolean camposInvalido() throws Exception {

		if (idEscola == null || idEscola.equals(0l)) {
			FacesMessagesUtil.addErrorMessage("Escola", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}
		if (getModel().getTurma() == null || getModel().getTurma().isEmpty()) {
			FacesMessagesUtil.addErrorMessage("Nome", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}
		if (getModel().getAno() == null || getModel().getAno().isEmpty()) {
			FacesMessagesUtil.addErrorMessage("Ano", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}
		if (getModel().getInicio() == null) {
			FacesMessagesUtil.addErrorMessage("Início", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}
		if (idProfessor == null || idProfessor.equals(0l)) {
			FacesMessagesUtil.addErrorMessage("Professor", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}
		if (getModel().getEnsino() == null || getModel().getEnsino().isEmpty()) {
			FacesMessagesUtil.addErrorMessage("Ensino", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}
		if (getModel().getTermino() == null) {
			FacesMessagesUtil.addErrorMessage("Término", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}

		if (getModel().getInicio().after(getModel().getTermino())) {
			FacesMessagesUtil.addErrorMessage("Data de Início", "é posterior a data de término");
			return true;
		}

		return false;
	}

	public UniversalManager getUniversalManager() {
		return universalManager;
	}

	public void setUniversalManager(UniversalManager universalManager) {
		this.universalManager = universalManager;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public void verificarAcesso() {
	}

	@Override
	public Turma createModel() {
		return new Turma();
	}

	@Override
	public String getQualifiedName() {
		return "Turma";
	}

	@Override
	public boolean isFeminino() {
		return false;
	}

	@Override
	public Map<String, String> getFilters() {
		return null;
	}

	public List<Escola> getEscolas() {
		return escolas;
	}

	public void setEscolas(List<Escola> escolas) {
		this.escolas = escolas;
	}

	public Long getIdEscola() {
		return idEscola;
	}

	public void setIdEscola(Long idEscola) {
		this.idEscola = idEscola;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public Long getIdProfessor() {
		return idProfessor;
	}

	public void setIdProfessor(Long idProfessor) {
		this.idProfessor = idProfessor;
	}

}
