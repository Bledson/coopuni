package br.edu.ufrn.imd.coopuni.service;

import br.edu.ufrn.imd.coopuni.boundary.GeolocationDAO;
import br.edu.ufrn.imd.coopuni.model.Area;
import br.edu.ufrn.imd.coopuni.model.Geolocation;

import javax.inject.Inject;

/**
 * Created by andreza on 05/11/15.
 */
public class GeolocationService {


    @Inject
    GeolocationDAO geolocationDAO;

    public Geolocation retrieveById(long id) {
        return geolocationDAO.find(id);
    }

    public void register(Float latitude, Float longitude, Area area){
        Geolocation geolocation = new Geolocation();
        geolocation.setLatitude(latitude);
        geolocation.setLongitude(longitude);
        geolocation.setArea(area);
        geolocationDAO.create(geolocation);
    }
}
