package br.edu.ufrn.imd.coopuni.service;

import br.edu.ufrn.imd.coopuni.boundary.CategoryDAO;
import br.edu.ufrn.imd.coopuni.model.Category;

import javax.inject.Inject;

/**
 * Created by andreza on 05/11/15.
 */
public class CategoryService {

    @Inject
    CategoryDAO categoryDAO;

    public Category find(long id) {
        return categoryDAO.find(id);
    }
}
