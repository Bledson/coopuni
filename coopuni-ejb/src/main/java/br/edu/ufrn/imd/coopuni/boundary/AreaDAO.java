package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Area;

import java.util.List;

public interface AreaDAO extends AbstractDAO<Long, Area> {
  public void delete(Area entity) throws Exception;

  public List<Area> findAllOrderedByName();
}
