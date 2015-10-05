package br.edu.ufrn.imd.coopuni.service;

import br.edu.ufrn.imd.coopuni.boundary.PostDAO;
import br.edu.ufrn.imd.coopuni.model.Area;
import br.edu.ufrn.imd.coopuni.model.Post;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class PostService {
  @Inject
  private AreaService areaService;

  @Inject
  private Logger log;

  @Inject
  private PostDAO postDAO;

  private List<Post> posts;

  public void register(Post post) throws Exception {
    postDAO.create(post);
  }

  public List<Area> getPostAreas() {
    return areaService.retrieveAllOrderedByName();
  }

  public List<Post> getPostsByUserId(long id) {
    return postDAO.findByUserId(id);
  }

  public Post get(long id) {
    return postDAO.find(id);
  }

  public void updateVote(String type, long postID) throws Exception {
    postDAO.updateVote(type, postID);
  }

  @Named
  @Produces
  public List<Post> getPosts() {
    return posts;
  }

  @PostConstruct
  public void retrieveAllOrderedByNewer() {
    posts = postDAO.findAllOrderedByNewer();
  }
}
