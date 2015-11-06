package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Comment;

import java.util.List;

public interface CommentDAO extends AbstractDAO<Long, Comment> {
  public void delete(Comment entity) throws Exception;

  public List<Comment> findAllOrderedByNewer();
}
