package br.com.esf.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.Hibernate;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.arquitetura.bean.PaginableBean;
import br.com.arquitetura.excecao.ExcecaoUtil;
import br.com.arquitetura.service.UniversalManager;
import br.com.arquitetura.util.FacesMessagesUtil;
import br.com.esf.entidade.Escola;
import br.com.esf.entidade.Estado;
import br.com.esf.util.Constantes;
import br.com.esf.util.ValidacaoUtil;

@ManagedBean(name = "escolaBean")
@ViewScoped
public class EscolaBean extends PaginableBean<Escola> {

	private static final long serialVersionUID = 1L;

	private List<Estado> estados;
	private List<Escola> filter;
	private Long idEstado;

	public EscolaBean() {
		estados = new ArrayList<Estado>();
	}

	@PostConstruct
	public void init() {

		try {

			if (estados.isEmpty()) {
				Estado estado = new Estado();
				estados = universalManager.listBy(estado);
				Collections.sort(estados, new Comparator<Estado>() {
					@Override
					public int compare(Estado o1, Estado o2) {
						return o1.getSigla().trim().compareTo(o2.getSigla());
					}
				});
			}

			listar();
		} catch (Exception e) {
			ExcecaoUtil.tratarExcecao(e);

		}

	}

	public List<Escola> listar() {
		try {
			return universalManager.listBy(new Escola());
		} catch (Exception e) {
			ExcecaoUtil.tratarExcecao(e);
		}

		return null;
	}

	@Override
	public String save() {
		try {

			if (camposInvalido()) {
				return "";
			}

			getModel().setCep(ValidacaoUtil.somenteNumero(getModel().getCep()));
			getModel().setEstado(new Estado(idEstado));
			idEstado = null;
			// TODO retirar
			getModel().setEstadoSigla(getModel().getEstado().getId().intValue());

			super.save();
			setModel(new Escola());
		} catch (Exception e) {
			ExcecaoUtil.tratarExcecao(e);
		}

		return "";
	}

	private boolean camposInvalido() throws Exception {

		if (getModel().getNome() == null || getModel().getNome().isEmpty()) {
			FacesMessagesUtil.addErrorMessage("Nome", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}
		if (getModel().getNumero() == null || getModel().getNumero().isEmpty()) {
			FacesMessagesUtil.addErrorMessage("Número", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}
		if (getModel().getCidade() == null || getModel().getCidade().isEmpty()) {
			FacesMessagesUtil.addErrorMessage("Cidade", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}
		if (getModel().getLogradouro() == null || getModel().getLogradouro().isEmpty()) {
			FacesMessagesUtil.addErrorMessage("Logradouro", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}
		if (getModel().getLogradouro() == null || getModel().getLogradouro().isEmpty()) {
			FacesMessagesUtil.addErrorMessage("Logradouro", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}
		if (getModel().getBairro() == null || getModel().getBairro().isEmpty()) {
			FacesMessagesUtil.addErrorMessage("Bairro", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}
		if (idEstado == null || idEstado.equals(0l)) {
			FacesMessagesUtil.addErrorMessage("Estado", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}
		if (getModel().getCep() == null || getModel().getCep().isEmpty()) {
			FacesMessagesUtil.addErrorMessage("CEP", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}
		if (getModel().getEmail() == null || getModel().getEmail().isEmpty()) {
			FacesMessagesUtil.addErrorMessage("Email", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}
		if (getModel().getTipo() == null || getModel().getTipo().isEmpty()) {
			FacesMessagesUtil.addErrorMessage("Filial", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}

		return false;
	}

	@Override
	public String load() {
		super.load();
		idEstado = getModel().getEstado().getId();
		return "";
	}

	public UniversalManager getUniversalManager() {
		return universalManager;
	}

	public void setUniversalManager(UniversalManager universalManager) {
		this.universalManager = universalManager;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public void verificarAcesso() {
	}

	@Override
	public Escola createModel() {
		return new Escola();
	}

	@Override
	public String getQualifiedName() {
		return "Escola";
	}

	@Override
	public boolean isFeminino() {
		return false;
	}

	public List<Escola> getFilter() {
		return filter;
	}

	public void setFilter(List<Escola> filter) {
		this.filter = filter;
	}

	@Override
	public Map<String, String> getFilters() {
		return null;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

}
