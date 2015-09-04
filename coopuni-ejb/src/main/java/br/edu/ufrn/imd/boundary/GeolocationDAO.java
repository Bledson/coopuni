package br.edu.ufrn.imd.boundary;

import br.edu.ufrn.imd.model.Geolocation;

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
  public void retrieve(Long id) {

  }

  @Override
  public void update(Geolocation entity) {

  }
}
