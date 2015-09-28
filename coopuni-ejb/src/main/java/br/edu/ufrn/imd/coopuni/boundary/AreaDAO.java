package br.edu.ufrn.imd.coopuni.boundary;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edu.ufrn.imd.coopuni.model.Area;

public class AreaDAO implements AbstractDAO<Long,  Area> {

	@Inject
	private EntityManager em;
	
	private static final AreaDAO instance = new AreaDAO();
	
	public static AreaDAO getInstance() {
		return instance;
	}
	
	private AreaDAO() {}
	
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
        CriteriaQuery<Area> cq = cb.createQuery(Area.class);
        Root<Area> rootEntry = cq.from(Area.class);
        CriteriaQuery<Area> all = cq.select(rootEntry);
        TypedQuery<Area> allQuery = em.createQuery(all);
        return allQuery.getResultList();
	}
	
	
	

	
}
