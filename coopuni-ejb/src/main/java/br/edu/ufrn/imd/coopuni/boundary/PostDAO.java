package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Post;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class PostDAO implements AbstractDAO<Long, Post> {
  @Inject
  private EntityManager em;

  @Override
  public void create(Post entity) {

  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public void retrieve(Long id) {

  }

  @Override
  public void update(Post entity) {

  }
}
