package br.com.arquitetura.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

@ManagedBean(name = "menuBean")
@SessionScoped
public class MenuBean {

	private MenuModel painelPrincipal;
	private MenuModel planejamento;
	private MenuModel perfil;

	public MenuModel getPainelPrincipal() {
		painelPrincipal = new DefaultMenuModel();

		// Principal
		// --------------------------------------------------------------------------------------------------------------------------------------------
		Submenu submenu = new Submenu();
		submenu.setLabel("Painel Principal");

		// HOME
		MenuItem item = new MenuItem();

		item = new MenuItem();
		item.setValue("Home");
		item.setUrl("/pages/principal.jsf");
		painelPrincipal.addMenuItem(item);
		submenu.getChildren().add(item);

		return painelPrincipal;
	}

	public MenuModel getPlanejamento() {
		planejamento = new DefaultMenuModel();

		// Principal
		// --------------------------------------------------------------------------------------------------------------------------------------------
		Submenu submenu = new Submenu();
		submenu.setLabel("Planejamento");

		// HOME
		MenuItem item = new MenuItem();

		item = new MenuItem();
		item.setValue("Planejamento");
		item.setUrl("/pages/principal.jsf");
		planejamento.addMenuItem(item);
		submenu.getChildren().add(item);

		return planejamento;
	}

	

	public MenuModel getPerfil() {
		perfil = new DefaultMenuModel();

		// Principal
		// --------------------------------------------------------------------------------------------------------------------------------------------
		Submenu submenu = new Submenu();
		submenu.setLabel("Perfil");

		// HOME
		MenuItem item = new MenuItem();

		item = new MenuItem();
		item.setValue("Atualizar Informações");
		item.setUrl("/pages/cadastroNoivaCompleto.jsf");
		perfil.addMenuItem(item);
		submenu.getChildren().add(item);

		return perfil;
	}

	public void setPerfil(MenuModel perfil) {
		this.perfil = perfil;
	}
	public void setPlanejamento(MenuModel planejamento) {
		this.planejamento = planejamento;
	}

	public void setPainelPrincipal(MenuModel painelPrincipal) {
		this.painelPrincipal = painelPrincipal;
	}

}
