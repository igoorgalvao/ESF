package br.com.esf.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.Column;

import br.com.arquitetura.excecao.ExcecaoUtil;
import br.com.arquitetura.service.UniversalManager;
import br.com.esf.entidade.Acesso;
import br.com.esf.entidade.Responsavel;
import br.com.esf.entidade.Usuario;
import br.com.esf.util.CriptoUtil;

@ManagedBean(name = "banco")
@ApplicationScoped
public class BancoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{universalManager}")
	public UniversalManager universalManager;

	private Boolean jaPopulado;

	public void popularBase() {

		try {
			if (jaPopulado == null || !jaPopulado) {

				Acesso acesso = new Acesso();
				acesso.setLogin("admin");
				
				List<Acesso> listaAcesso = universalManager.listBy(acesso);

				if (listaAcesso == null || listaAcesso.isEmpty()) {
					
					acesso.setSenha(CriptoUtil.getCriptografia("admin"));
					
					Usuario user = new Usuario();
					user.setEmail("admin@admin.com.br");
					user.setNome("admin");
					universalManager.save(user);

					acesso.setUsuario(user);
					universalManager.save(acesso);
				
				}

				acesso = new Acesso();
				acesso.setLogin("igor");
				listaAcesso = universalManager.listBy(acesso);

				if (listaAcesso == null || listaAcesso.isEmpty()) {
					
					acesso.setSenha(CriptoUtil.getCriptografia("igor"));

					Responsavel resp = new Responsavel();
					resp.setNome("igor");
					resp.setParentesco("Pai");
					resp.setTipo("1");
					resp.setEmail("igor.galvao1@gmail.com");
					resp.setCadastraOutros(false);
					universalManager.save(resp);

					acesso.setResponsavel(resp);
					universalManager.save(acesso);
				}
			}

			jaPopulado = true;

		} catch (Exception e) {
			jaPopulado = false;
			ExcecaoUtil.tratarExcecao(e);
		}
	}

	public Boolean getJaPopulado() {
		return jaPopulado;
	}

	public void setJaPopulado(Boolean jaPopulado) {
		this.jaPopulado = jaPopulado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UniversalManager getUniversalManager() {
		return universalManager;
	}

	public void setUniversalManager(UniversalManager universalManager) {
		this.universalManager = universalManager;
	}

}
