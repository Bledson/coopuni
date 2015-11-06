package br.edu.ufrn.imd.coopuni.service;

import br.edu.ufrn.imd.coopuni.boundary.CommentDAO;
import br.edu.ufrn.imd.coopuni.boundary.PostDAO;
import br.edu.ufrn.imd.coopuni.model.Comment;
import br.edu.ufrn.imd.coopuni.model.Post;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;

@Stateless
public class CommentService {
  @Inject
  private CommentDAO commentDAO;

  @Inject
  private Logger log;

  @Inject
  private PostDAO postDAO;

  public void register(Comment comment, long postID) throws Exception {
    log.info("Registrando comentario");
    Post post = postDAO.find(postID);
    //commentDAO.createcomment(comment, post);
  }
}
