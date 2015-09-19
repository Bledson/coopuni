package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Geolocation;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class GeolocationDAO implements AbstractDAO<Long, Geolocation> {
  @Inject
  private EntityManager em;

  @Override
  public void create(Geolocation entity) {

  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public Geolocation retrieve(Long id) {
    return null;
  }

  @Override
  public void update(Geolocation entity) {

  }
}
