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
import br.com.arquitetura.util.FacesMessagesUtil;
import br.com.esf.entidade.Acesso;
import br.com.esf.entidade.Escola;
import br.com.esf.entidade.Usuario;
import br.com.esf.util.Constantes;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean extends PaginableBean<Acesso> {

	private static final long serialVersionUID = 1L;

	private List<Escola> escolas;
	private Long idEscola;

	public UsuarioBean() {
		escolas = new ArrayList<Escola>();
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

		} catch (Exception e) {
			ExcecaoUtil.tratarExcecao(e);

		}

	}

	@Override
	public String save() {
		try {

			if (camposInvalido()) {
				return "";
			}

		} catch (Exception e) {
			ExcecaoUtil.tratarExcecao(e);
		}

		return "";
	}

	private boolean camposInvalido() throws Exception {

		if (getModel().getUsuario().getNome() == null || getModel().getUsuario().getNome().isEmpty()) {
			FacesMessagesUtil.addErrorMessage("Nome", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}

		if (getModel().getUsuario().getEmail() == null || getModel().getUsuario().getEmail().isEmpty()) {
			FacesMessagesUtil.addErrorMessage("E-mail", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}
		if (getModel().getLogin() == null || getModel().getLogin().isEmpty()) {
			FacesMessagesUtil.addErrorMessage("Login", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}
		if (getModel().getSenha() == null || getModel().getSenha().isEmpty()) {
			FacesMessagesUtil.addErrorMessage("Senha", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}
		if (idEscola == null || idEscola.equals(0l)) {
			FacesMessagesUtil.addErrorMessage("Escola", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}

		return false;
	}

	@Override
	public String load() {
		super.load();
		if(getModel().getUsuario() != null && getModel().getUsuario().getEscola() != null){
			idEscola = getModel().getUsuario().getEscola().getId();
		}
		return "";
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
	public Acesso createModel() {
		Usuario usu = new Usuario();
		usu.setEscola(new Escola());
		Acesso acesso = new Acesso();
		acesso.setUsuario(usu);
		return acesso;
	}

	@Override
	public String getQualifiedName() {
		return "Escola";
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

}
