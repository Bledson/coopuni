package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Comment;

import java.util.List;

public interface CommentDAO extends AbstractDAO<Long, Comment> {
  void delete(Comment entity) throws Exception;

  List<Comment> findAllByPostId(Long id);

  List<Comment> findAllByUserId(Long id);

  List<Comment> findAllOrderedByNewer();
}
