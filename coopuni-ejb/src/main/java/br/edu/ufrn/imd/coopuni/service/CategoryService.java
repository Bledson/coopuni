package br.edu.ufrn.imd.coopuni.service;

import br.edu.ufrn.imd.coopuni.boundary.CategoryDAO;
import br.edu.ufrn.imd.coopuni.model.Category;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CategoryService {
  @Inject
  private CategoryDAO categoryDAO;

  public Category retrieve(long id) {
    return categoryDAO.find(id);
  }

  public List<Category> retrieveAllOrderedByName() {
    return categoryDAO.findAllOrderedByName();
  }
}
