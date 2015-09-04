package br.edu.ufrn.imd.boundary;

import br.edu.ufrn.imd.model.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class UserDAO implements AbstractDAO<Long, User> {
  @Inject
  private EntityManager em;

  @Override
  public void create(User entity) {
  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public void retrieve(Long id) {

  }

  @Override
  public void update(User entity) {

  }
}
