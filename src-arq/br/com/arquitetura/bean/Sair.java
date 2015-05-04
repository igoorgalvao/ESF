package br.com.arquitetura.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.arquitetura.excecao.ExcecaoUtil;
import br.com.arquitetura.util.FacesUtil;

@ManagedBean(name = "sair")
@ViewScoped
public class Sair {

	public void logout() {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			session.invalidate();
			FacesUtil.redirecionarCaminhoCompleto("/j_spring_security_logout");
		} catch (Exception e) {
			ExcecaoUtil.tratarExcecao(e);
		}
	}
	
	

}
