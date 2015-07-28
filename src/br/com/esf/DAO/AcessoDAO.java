package br.com.esf.DAO;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import br.com.esf.entidade.Acesso;

public interface AcessoDAO {

	Long countAcesso(Acesso model,Map filters) throws Exception;

	List<Acesso> paginateAcesso(int first, int pageSize, Acesso model, String sortField, SortOrder sortOrder, Map filters) throws Exception;
}
