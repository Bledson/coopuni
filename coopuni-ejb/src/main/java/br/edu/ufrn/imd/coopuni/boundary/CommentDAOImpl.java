package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Comment;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class CommentDAOImpl implements CommentDAO {
  @Inject
  private EntityManager em;

  @Override
  public void create(Comment entity) throws Exception {
    em.persist(entity);
  }

  @Override
  public void delete(Comment entity) throws Exception {
    em.remove(entity);
  }

  @Override
  public void delete(Long id) throws Exception {
    em.remove(em.find(Comment.class, id));
  }

  @Override
  public Comment find(Long id) {
    return em.find(Comment.class, id);
  }

  @Override
  public List<Comment> findAllOrderedByNewer() {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Comment> c = cb.createQuery(Comment.class);
    Root<Comment> comment = c.from(Comment.class);
    c.select(comment).orderBy(cb.asc(comment.get("createdAt")));
    return em.createQuery(c).getResultList();
  }

  @Override
  public void update(Comment entity) throws Exception {
    em.merge(entity);
  }
}
