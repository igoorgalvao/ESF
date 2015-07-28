package br.com.esf.service;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.arquitetura.DAO.UniversalDAO;
import br.com.esf.DAO.AcessoDAO;
import br.com.esf.entidade.Acesso;
import br.com.esf.entidade.Escola;
import br.com.esf.entidade.Usuario;

@Service(value = "acessoService")
@Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
public class AcessoServiceImpl implements AcessoService {

	@Autowired(required = true)
	@Qualifier(value = "universalDAO")
	protected UniversalDAO dao;

	@Autowired(required = true)
	@Qualifier(value = "acessoDAO")
	protected AcessoDAO acessoDAO;

	@Override
	public Long countAcesso(Acesso model,Map filters) throws Exception {
		return acessoDAO.countAcesso(model,filters);
	}

	@Override
	public List<Acesso> paginateAcesso(int first, int pageSize, Acesso model, String sortField, SortOrder sortOrder, Map filters)
			throws Exception {
		return acessoDAO.paginateAcesso(first, pageSize, model, sortField, sortOrder, filters);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void save(Acesso model) throws Exception {
		//salva usuario
		Usuario us = model.getUsuario();
		us.setEscola((Escola) dao.listBy(us.getEscola()).get(0));;
		dao.save(us);
		
		//Salva acesso
		model.setUsuario(us);
		dao.save(model);
	}

}
