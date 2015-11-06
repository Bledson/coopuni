package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Category;

import java.util.List;

public interface CategoryDAO extends AbstractDAO<Long, Category> {
  public List<Category> findAllOrderedByName();
}
