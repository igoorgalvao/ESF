package br.com.esf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.arquitetura.excecao.ExcecaoUtil;
import br.com.arquitetura.service.UniversalManager;
import br.com.esf.entidade.Estado;

@ManagedBean(name = "escolaBean")
@ViewScoped
public class EscolaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{universalManager}")
	public UniversalManager universalManager;

	private List<Estado> estados;

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
		} catch (Exception e) {
			ExcecaoUtil.tratarExcecao(e);

		}

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

}
