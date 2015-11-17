package br.edu.ufrn.imd.coopuni.service;

import br.edu.ufrn.imd.coopuni.boundary.GeolocationDAO;
import br.edu.ufrn.imd.coopuni.model.Geolocation;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class GeolocationService {
  @Inject
  GeolocationDAO geolocationDAO;

  public void create(Geolocation geolocation) throws Exception {
    geolocationDAO.create(geolocation);
  }

  public Geolocation retrieve(long id) {
    return geolocationDAO.find(id);
  }
}
