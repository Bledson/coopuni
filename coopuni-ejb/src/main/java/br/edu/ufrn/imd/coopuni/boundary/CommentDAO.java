package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Comment;
import br.edu.ufrn.imd.coopuni.model.Post;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CommentDAO implements AbstractDAO<Long, Comment> {
  @Inject
  private EntityManager em;

  @Override
  public void create(Comment entity) {
      em.persist(entity);
  }

  public void createcomment(Comment entity,Post post) {
      entity.setPost(post);
      this.create(entity);
  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public Comment find(Long id) {
    return em.find(Comment.class, id);
  }

  @Override
  public void update(Comment entity) {
    em.merge(entity);
  }
}
