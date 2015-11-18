package br.edu.ufrn.imd.coopuni.service;

import br.edu.ufrn.imd.coopuni.boundary.CommentDAO;
import br.edu.ufrn.imd.coopuni.model.Comment;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CommentService {
  @Inject
  private CommentDAO commentDAO;

  @Inject
  private Logger log;

  public void create(Comment comment) throws Exception {
    log.info("Registrando comentario");
    commentDAO.create(comment);
  }

  public Comment retrieve(long id) {
    return commentDAO.find(id);
  }

  public List<Comment> retrieveAllOrderedByNewer() {
    return commentDAO.findAllOrderedByNewer();
  }

  public List<Comment> retrieveAllByUserId(long id) {
    return commentDAO.findAllByUserId(id);
  }

  public List<Comment> retrieveAllByPostId(long id) {
    return commentDAO.findAllByPostId(id);
  }
}
