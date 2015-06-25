package br.com.esf.bean;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "style")
@ApplicationScoped
public class StyleBean implements Serializable {

	private static final long serialVersionUID = 1L;

	
	public String getStyleCampo(){
		return "background-color:#4D94D3;color:white;border-radius: 10px;";
	}
	
}
