package br.edu.ufrn.imd.coopuni.boundary;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edu.ufrn.imd.coopuni.model.Area;
import br.edu.ufrn.imd.coopuni.model.Member;

@ApplicationScoped  
public class AreaDAO implements AbstractDAO<Long,  Area> {

	@Inject
	private EntityManager em;
	
	
	@Override
	public void create(Area entity) throws Exception {
		em.persist(entity);
	}

	@Override
	public void delete(Long id) {
		
	}

	@Override
	public Area retrieve(Long id) {
		return em.find(Area.class, id);
	}

	@Override
	public void update(Area entity) {
		
	}

	public List<Area> getAllEntries() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Area> c = cb.createQuery(Area.class);
		Root<Area> member = c.from(Area.class);
		c.select(member);
		List<Area> result = em.createQuery(c).getResultList();
		return result;
	}
	
	
	

	
}
