package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Geolocation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class GeolocationDAOImpl implements GeolocationDAO {
  @Inject
  private EntityManager em;

  @Override
  public void create(Geolocation entity) throws Exception {
    em.persist(entity);
  }

  @Override
  public void delete(Geolocation entity) throws Exception {
    em.remove(entity);
  }

  @Override
  public void delete(Long id) throws Exception {
    em.remove(em.find(Geolocation.class, id));
  }

  @Override
  public Geolocation find(Long id) {
    return em.find(Geolocation.class, id);
  }

  @Override
  public void update(Geolocation entity) throws Exception {
    em.merge(entity);
  }
}
