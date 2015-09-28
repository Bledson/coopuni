package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Member;
import br.edu.ufrn.imd.coopuni.model.Post;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class PostDAO implements AbstractDAO<Long, Post> {
  
  @Inject
  private EntityManager em;

  @Override
  public void create(Post entity) {
    em.persist(entity);
  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public Post retrieve(Long id) {
    return null;
  }

  @Override
  public void update(Post entity) {

  }


  public List<Post> getPostsByUser(Member member) {
    return null;
  }

}
