package br.com.esf.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.arquitetura.bean.BaseBean;
import br.com.esf.entidade.Usuario;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean extends BaseBean<Usuario> {

	private static final long serialVersionUID = 1L;

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

}
