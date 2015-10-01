package br.edu.ufrn.imd.coopuni.service;

import br.edu.ufrn.imd.coopuni.boundary.PostDAO;
import br.edu.ufrn.imd.coopuni.model.Area;
import br.edu.ufrn.imd.coopuni.model.Member;
import br.edu.ufrn.imd.coopuni.model.Post;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class PostService {

  @Inject
  private PostDAO postDAO;
  
  @Inject
  private AreaService areaService;
  
  public void register(Post post) throws Exception {
    postDAO.create(post);
  }
 
  public List<Area> getPostAreas() {
	  return areaService.getAllEntries();
  }
  
  public List<Post> getPostsByUserId(long id) {
    return postDAO.getPostsByUserId(id);
  }
  
  public Post retrive(long id) {
	  return postDAO.retrieve(id);
  }

  public void updateVote(String type, long postID)  throws Exception {
	  postDAO.updateVote(type, postID);
  }
 
  public List<Post> getAllPosts() {
	  return postDAO.getAllPosts();
  }
}
