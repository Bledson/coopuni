package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Post;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class PostDAOImpl implements PostDAO {
  @Inject
  private EntityManager em;

  @Override
  public void create(Post entity) throws Exception {
    em.persist(entity);
  }

  @Override
  public void delete(Post entity) throws Exception {
    em.remove(entity);
  }

  @Override
  public void delete(Long id) throws Exception {
    em.remove(em.find(PostDAO.class, id));
  }

  @Override
  public Post find(Long id) {
    return em.find(Post.class, id);
  }

  @Override
  public List<Post> findAllByUserId(Long id) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Post> c = cb.createQuery(Post.class);
    Root<Post> post = c.from(Post.class);
    c.select(post).where(cb.equal(post.get("member").get("id"), id));
    return em.createQuery(c).getResultList();
  }

  @Override
  public List<Post> findAllOrderedByLikes() {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Post> c = cb.createQuery(Post.class);
    Root<Post> post = c.from(Post.class);
    c.select(post).orderBy(cb.desc(post.get("likes")));
    return em.createQuery(c).getResultList();
  }

  @Override
  public List<Post> findAllOrderedByNewer() {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Post> c = cb.createQuery(Post.class);
    Root<Post> post = c.from(Post.class);
    c.select(post).orderBy(cb.desc(post.get("createdAt")));
    return em.createQuery(c).getResultList();
  }

  @Override
  public void update(Post entity) throws Exception {
    em.merge(entity);
  }

  public void updateVote(String type, long postID) {
    Post post = this.find(postID);

    CriteriaBuilder cb = this.em.getCriteriaBuilder();
    CriteriaUpdate<Post> update = cb.createCriteriaUpdate(Post.class);
    Root<Post> e = update.from(Post.class);

    if (type.equals("like")) {
      int likes = post.getLikes();
      update.set("likes", likes + 1);
    } else {
      if (type.equals("down")) {
        int down = post.getDownvotes();
        update.set("downvotes", down + 1);
      }
    }

    update.where(cb.equal(e.get("id"), postID));
    this.em.createQuery(update).executeUpdate();
  }
}
