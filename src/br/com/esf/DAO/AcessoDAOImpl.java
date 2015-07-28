package br.com.esf.DAO;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import br.com.arquitetura.DAO.UniversalDAO;
import br.com.arquitetura.util.CriteriaMC;
import br.com.esf.entidade.Acesso;

@Repository(value = "acessoDAO")
public class AcessoDAOImpl extends HibernateDaoSupport implements AcessoDAO {

	@Autowired(required = true)
	public void setFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	@Autowired(required = true)
	@Qualifier(value = "universalDAO")
	protected UniversalDAO dao;

	@Override
	public Long countAcesso(Acesso model, Map filters) throws Exception {
		Criteria c = retornarCriteria(model, filters);
		c.setProjection(Projections.rowCount());
		Long result = (Long) c.list().get(0);
		return result;
	}

	private Criteria retornarCriteria(Acesso model, Map filters) {
		Criteria criteria = getSession().createCriteria(Acesso.class);
		criteria.createAlias("usuario", "us");
		criteria.add(Restrictions.isNotNull("us.id"));
		criteria.add(Restrictions.not(Restrictions.like("login", "admin")));

		if (filters != null) {
			Set<String> chaves = filters.keySet();
			for (String fil : chaves) {

				if (fil.trim().equalsIgnoreCase("usuario.nome")) {
					criteria.add(Restrictions.ilike("us.nome", (String) filters.get(fil), MatchMode.ANYWHERE));
				}
				if (fil.trim().equalsIgnoreCase("login")) {
					criteria.add(Restrictions.ilike("login", (String) filters.get(fil), MatchMode.ANYWHERE));
				}
				if (fil.trim().equalsIgnoreCase("usuario.escola.nome")) {
					criteria.createAlias("us.escola", "escola");
					criteria.add(Restrictions.ilike("escola.nome", (String) filters.get(fil), MatchMode.ANYWHERE));
				}
			}
		}

		return criteria;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Acesso> paginateAcesso(int first, int pageSize, Acesso model, String sortField, SortOrder sortOrder, Map filters)
			throws Exception {
		Criteria c = retornarCriteria(model, filters);

		if (first != 0)
			c.setFirstResult(first);
		if (pageSize != 0)
			c.setMaxResults(pageSize);

		CriteriaMC criteriaMC = new CriteriaMC();
		criteriaMC.setCriteria(c);

		boolean sort = false;
		if (sortOrder.equals(SortOrder.DESCENDING)) {
			sort = true;
		}

		criteriaMC = dao.addOrderBy(criteriaMC, sortField, sort);

		return criteriaMC.getCriteria().list();
	}

}
