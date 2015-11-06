package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Post;

import java.util.List;

public interface PostDAO extends AbstractDAO<Long, Post> {
  public void delete(Post entity) throws Exception;

  List<Post> findAllOrderedByNewer();

  List<Post> findByUserId(Long id);
}
