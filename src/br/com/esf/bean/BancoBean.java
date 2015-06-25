package br.com.esf.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.Column;

import br.com.arquitetura.excecao.ExcecaoUtil;
import br.com.arquitetura.service.UniversalManager;
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

				Usuario user1 = new Usuario();
				user1.setLogin("admin");
				List<Usuario> listaAcesso = universalManager.listBy(user1);

				if (listaAcesso == null || listaAcesso.isEmpty()) {
					user1.setEmail("admin@admin.com.br");
					user1.setNome("admin");
					user1.setSenha(CriptoUtil.getCriptografia("admin"));
					universalManager.save(user1);
				}

				Responsavel resp = new Responsavel();
				resp.setLogin("igor");
				List<Responsavel> listaResp = universalManager.listBy(resp);

				if (listaResp == null || listaResp.isEmpty()) {
					resp.setNome("igor");
					resp.setParentesco("Pai");
					resp.setTipo("1");
					resp.setEmail("igor.galvao1@gmail.com");
					resp.setCadastraOutros(false);
					resp.setLogin("igor");
					resp.setSenha(CriptoUtil.getCriptografia("admin"));
					universalManager.save(resp);
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
