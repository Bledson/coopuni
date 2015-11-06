package br.edu.ufrn.imd.coopuni.service;

import br.edu.ufrn.imd.coopuni.boundary.GeolocationDAO;
import br.edu.ufrn.imd.coopuni.model.Area;
import br.edu.ufrn.imd.coopuni.model.Geolocation;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class GeolocationService {
  @Inject
  GeolocationDAO geolocationDAO;

  public void register(Float latitude, Float longitude, Area area) throws Exception {
    Geolocation geolocation = new Geolocation();
    geolocation.setLatitude(latitude);
    geolocation.setLongitude(longitude);
    geolocation.setArea(area);
    geolocationDAO.create(geolocation);
  }

  public Geolocation retrieve(long id) {
    return geolocationDAO.find(id);
  }
}
