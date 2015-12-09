package br.edu.ufrn.imd.coopuni.service;

import br.edu.ufrn.imd.coopuni.boundary.PostDAO;
import br.edu.ufrn.imd.coopuni.model.Post;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class PostService {
  @Inject
  private Logger log;

  @Inject
  private PostDAO postDAO;

  public void create(Post post) throws Exception {
    postDAO.create(post);
  }

  public List<Post> retrieveAllOrderedByLikes(long id) {
    return postDAO.findAllOrderedByLikes();
  }

  public List<Post> retrieveAllOrderedByNewer() {
    return postDAO.findAllOrderedByNewer();
  }

  public List<Post> retrievePostsByUserId(long id) {
    return postDAO.findAllByUserId(id);
  }

  public Post retrieve(long id) {
    return postDAO.find(id);
  }

  public List<Post> retrieveAllByUserId(long id) {
    return postDAO.findAllByUserId(id);
  }

  public void vote (String type,long id){
    postDAO.updateVote(type,id);
  }
}
