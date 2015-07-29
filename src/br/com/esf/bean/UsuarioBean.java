package br.com.esf.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.arquitetura.bean.PaginableBean;
import br.com.arquitetura.excecao.ExcecaoUtil;
import br.com.arquitetura.service.UniversalManager;
import br.com.arquitetura.util.ConstantesARQ;
import br.com.arquitetura.util.FacesMessagesUtil;
import br.com.esf.entidade.Acesso;
import br.com.esf.entidade.Escola;
import br.com.esf.entidade.Estado;
import br.com.esf.entidade.Usuario;
import br.com.esf.service.AcessoService;
import br.com.esf.util.Constantes;
import br.com.esf.util.CriptoUtil;
import br.com.esf.util.ValidacaoUtil;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean extends PaginableBean<Acesso> {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{acessoService}")
	protected AcessoService acessoService;

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
	
	public void limpar(){
		setModel(createModel());
		idEscola=null;
	}

	@Override
	public String save() {
		try {

			if (camposInvalido()) {
				return "";
			}

			getModel().getUsuario().setEscola(new Escola(idEscola));

			//Senha
			if(getModel().getId() != null){
				//verifica se a senha digitada é nova
				Acesso ac = new Acesso();
				ac.setId(getModel().getId());
				ac = (Acesso) universalManager.listBy(ac).get(0);
				if(getModel().getSenha().equals(ac.getSenha())){
					getModel().setSenha(ac.getSenha());
				}else{
					//Criptografa a senha
					getModel().setSenha(CriptoUtil.getCriptografia(getModel().getSenha()));
				}
				
			}
			
			acessoService.save(getModel());
			limpar();
			FacesMessagesUtil.addInfoMessage(this.getQualifiedName(), this.getSaveMessage() + " " + ConstantesARQ.COM_SUCESSO);
		} catch (Exception e) {
			FacesMessagesUtil.addErrorMessage(this.getQualifiedName(), ConstantesARQ.ERRO_SALVAR);
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
		
		if(!ValidacaoUtil.isEmailValid(getModel().getUsuario().getEmail())){
			FacesMessagesUtil.addErrorMessage("E-mail", "Inválido");
			return true;
		}
		
		if (getModel().getLogin() == null || getModel().getLogin().isEmpty()) {
			FacesMessagesUtil.addErrorMessage("Login", Constantes.CAMPO_OBRIGATORIO);
			return true;
		}
		
		//verifica se o login já foi usado
		Acesso acesso = new Acesso();
		acesso.setLogin(getModel().getLogin());
		if(universalManager.listBy(acesso) != null && universalManager.listBy(acesso).size()>0 
				&&   !((Acesso)universalManager.listBy(acesso).get(0)).getId().equals(getModel().getId())  ){
			FacesMessagesUtil.addErrorMessage("Login", "Já em uso.");
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
		if (getModel().getUsuario() != null && getModel().getUsuario().getEscola() != null) {
			idEscola = getModel().getUsuario().getEscola().getId();
		}
		return "";
	}

	@Override
	public LazyDataModel<Acesso> getLazyDataModel() {
		if (lazyDataModel == null)

			lazyDataModel = new LazyDataModel<Acesso>() {

				private static final long serialVersionUID = -3128388997477577261L;

				@SuppressWarnings("rawtypes")
				@Override
				public List<Acesso> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map filters) {
					try {
						int rowCount = acessoService.countAcesso(getModel(),filters).intValue();
						lazyDataModel.setRowCount(rowCount);
						List<Acesso> list = acessoService.paginateAcesso(first, pageSize, getModel(), sortField, sortOrder, filters);
						return list;
					} catch (Exception e) {
						ExcecaoUtil.tratarExcecao(e);
					}
					return null;
				}
			};
		return lazyDataModel;
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
		Escola esc = new Escola();
		esc.setEstado(new Estado());
		Usuario usu = new Usuario();
		usu.setEscola(esc);
		Acesso acesso = new Acesso();
		acesso.setUsuario(usu);
		return acesso;
	}

	@Override
	public String getQualifiedName() {
		return "Usuário";
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

	public AcessoService getAcessoService() {
		return acessoService;
	}

	public void setAcessoService(AcessoService acessoService) {
		this.acessoService = acessoService;
	}

}
