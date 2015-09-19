package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Comment;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class CommentDAO implements AbstractDAO<Long, Comment> {
  @Inject
  private EntityManager em;

  @Override
  public void create(Comment entity) {

  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public Comment retrieve(Long id) {
    return null;
  }

  @Override
  public void update(Comment entity) {

  }
}
