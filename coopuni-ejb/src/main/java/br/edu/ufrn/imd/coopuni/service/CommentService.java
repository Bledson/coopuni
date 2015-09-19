package br.edu.ufrn.imd.coopuni.service;

import br.edu.ufrn.imd.coopuni.boundary.CommentDAO;
import br.edu.ufrn.imd.coopuni.model.Comment;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;

@Stateless
public class CommentService {

  @Inject
  private CommentDAO commentDAO;

  @Inject
  private Logger log;

  public void register(Comment comment) throws Exception {
    commentDAO.create(comment);
  }

}
