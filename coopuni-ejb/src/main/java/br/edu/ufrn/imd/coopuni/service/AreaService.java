package br.edu.ufrn.imd.coopuni.service;

import br.edu.ufrn.imd.coopuni.boundary.AreaDAOImpl;
import br.edu.ufrn.imd.coopuni.model.Area;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class AreaService {
  @Inject
  private AreaDAOImpl areaDAO;

  public void register(Area area) throws Exception {
    areaDAO.create(area);
  }

  public Area retrieve(long id) {
    return areaDAO.find(id);
  }

  public List<Area> retrieveAllOrderedByName() {
    return areaDAO.findAllOrderedByName();
  }

  public Area retrieveById(long id) {
    return areaDAO.find(id);
  }
}
