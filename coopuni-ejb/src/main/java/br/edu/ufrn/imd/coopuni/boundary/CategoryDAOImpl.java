package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class CategoryDAOImpl implements CategoryDAO {
  @Inject
  private EntityManager em;

  @Override
  public void create(Category entity) throws Exception {
  }

  @Override
  public void delete(Long id) throws Exception {
  }

  @Override
  public Category find(Long id) {
    return em.find(Category.class, id);
  }

  @Override
  public List<Category> findAllOrderedByName() {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Category> c = cb.createQuery(Category.class);
    Root<Category> category = c.from(Category.class);
    c.select(category).orderBy(cb.asc(category.get("name")));
    return em.createQuery(c).getResultList();
  }

  @Override
  public void update(Category entity) throws Exception {
  }
}
