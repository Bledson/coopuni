package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Geolocation;

public interface GeolocationDAO extends AbstractDAO<Long, Geolocation> {
  void delete(Geolocation entity) throws Exception;
}
