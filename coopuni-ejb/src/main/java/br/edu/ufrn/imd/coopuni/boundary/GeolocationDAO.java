package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Geolocation;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class GeolocationDAO implements AbstractDAO<Long, Geolocation> {
  @Inject
  private EntityManager em;

  @Override
  public void create(Geolocation entity) {
    em.persist(entity);
  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public Geolocation find(Long id) {
    return em.find(Geolocation.class, id);
  }

  @Override
  public void update(Geolocation entity) {
    em.merge(entity);
  }
}
