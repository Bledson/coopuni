package br.edu.ufrn.imd.coopuni.service;

import br.edu.ufrn.imd.coopuni.boundary.PostDAO;
import br.edu.ufrn.imd.coopuni.model.Member;
import br.edu.ufrn.imd.coopuni.model.Post;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class PostService {

  @Inject
  private PostDAO postDAO;


  public void register(Post post) throws Exception {
    postDAO.create(post);
  }

 

  public List<Post> getPostsByUser(Member member) {
    return null;
  }

 
}
