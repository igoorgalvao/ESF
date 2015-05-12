package br.com.esf.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.arquitetura.bean.BaseBean;
import br.com.arquitetura.excecao.ExcecaoUtil;
import br.com.arquitetura.util.FacesMessagesUtil;
import br.com.arquitetura.util.FacesUtil;
import br.com.esf.entidade.Usuario;
import br.com.esf.util.Constantes;
import br.com.esf.util.CriptoUtil;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean extends BaseBean<Usuario> {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{loginManager}")
	private AuthenticationManager am;
	
	@ManagedProperty(value = "#{banco}")
	private BancoBean banco;

	@Override
	public void init() {
		logout();
		setModel(new Usuario());
		banco.popularBase();
	}
	
	public String logout() {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			if (getModel() != null && getModel().getId() != null) {
				session.invalidate();
				FacesUtil.redirecionarCaminhoCompleto("/j_spring_security_logout");
			}
		} catch (Exception e) {
			ExcecaoUtil.tratarExcecao(e);
		}
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String logar() {
		try {
			if (getModel().getLogin() == null || getModel().getLogin().equals("")) {
				FacesMessagesUtil.addErrorMessage("Login ", Constantes.CAMPO_OBRIGATORIO);
				return "";
			}
			if (getModel().getSenha() == null || getModel().getSenha().equals("")) {
				FacesMessagesUtil.addErrorMessage("Senha ", Constantes.CAMPO_OBRIGATORIO);
				return "";
			}

			Authentication request = new UsernamePasswordAuthenticationToken(getModel().getLogin().trim(), getModel().getSenha().trim());
			Authentication result = am.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);

			// Coloca o usuário na sessão
			this.setModel(getUsuarioLogando());
			getSessionMap().put(Constantes.USUARIO_SESSAO, getModel());

			return redirect("/pages/principal.jsf");
		} catch (DisabledException e) {
			FacesMessagesUtil.addErrorMessage("Login ", " Usuário desabilitado");
			return "";
		} catch (AuthenticationException e) {
			Usuario u = getUsuarioLogando();
			if (u == null) {
				FacesMessagesUtil.addErrorMessage("Login ", " Usuário ou senha inválidos");
				return "";
			}
			try {
				if (!u.getSenha().equals(CriptoUtil.getCriptografia(getModel().getSenha()))) {
					FacesMessagesUtil.addErrorMessage("Login ", " Usuário ou senha inválidos");
					return "";
				}
			} catch (Exception e1) {
				ExcecaoUtil.tratarExcecao(e);
			}
		} catch (Exception e) {
			ExcecaoUtil.tratarExcecao(e);
		}

		return "";

	}

	public Usuario getUsuarioLogando() {
		try {
			Usuario us = new Usuario();
			us.setEmail(getModel().getEmail());

			List<Usuario> listaAcesso = universalManager.listBy(us, false);
			if (listaAcesso == null || listaAcesso.isEmpty()) {
				return null;
			} else {
				return listaAcesso.get(0);
			}
		} catch (Exception e) {
			ExcecaoUtil.tratarExcecao(e);
		}
		return null;

	}

	@Override
	public void verificarAcesso() {

	}

	@Override
	public Usuario createModel() {
		return new Usuario();
	}

	@Override
	public String getQualifiedName() {
		return "Usuário";
	}

	@Override
	public boolean isFeminino() {
		return false;
	}

	public AuthenticationManager getAm() {
		return am;
	}

	public void setAm(AuthenticationManager am) {
		this.am = am;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BancoBean getBanco() {
		return banco;
	}

	public void setBanco(BancoBean banco) {
		this.banco = banco;
	}

}
