package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Area;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class AreaDAOImpl implements AreaDAO {
  @Inject
  private EntityManager em;

  @Override
  public void create(Area entity) throws Exception {
    em.persist(entity);
  }

  @Override
  public void delete(Area entity) throws Exception {
    em.remove(entity);
  }

  @Override
  public void delete(Long id) throws Exception {
    em.remove(em.find(Area.class, id));
  }

  @Override
  public Area find(Long id) {
    return em.find(Area.class, id);
  }

  @Override
  public List<Area> findAllOrderedByName() {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Area> c = cb.createQuery(Area.class);
    Root<Area> area = c.from(Area.class);
    c.select(area).orderBy(cb.asc(area.get("name")));
    return em.createQuery(c).getResultList();
  }

  @Override
  public void update(Area entity) throws Exception {
    em.merge(entity);
  }
}
