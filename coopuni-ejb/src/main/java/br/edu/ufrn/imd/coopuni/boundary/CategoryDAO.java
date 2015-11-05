package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Area;
import br.edu.ufrn.imd.coopuni.model.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by andreza on 05/11/15.
 */

@ApplicationScoped
public class CategoryDAO  implements AbstractDAO<Long, Category> {

    @Inject
    private EntityManager em;

    @Override
    public void create(Category entity) throws Exception {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Category find(Long id) {
        return em.find(Category.class,id);
    }

    @Override
    public void update(Category entity) {

    }
}
