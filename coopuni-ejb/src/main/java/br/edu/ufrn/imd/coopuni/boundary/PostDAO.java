package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Member;
import br.edu.ufrn.imd.coopuni.model.Post;
import staticdata.StaticPosts;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class PostDAO implements AbstractDAO<Long, Post> {
  @Inject
  private EntityManager em;
  StaticPosts posts;
  
  public PostDAO() {
	  posts = new StaticPosts();
  }

  @Override
  public void create(Post entity) {
	  posts.setPosts(entity);	  
  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public void retrieve(Long id) {

  }

  @Override
  public void update(Post entity) {

  }

  public List<Post> getAll() {    
    return posts.getAll();
  }

  public List<Post> getPostsByUser(Member member) {
    return null;
  }

  public Post getPostById(int id) {
    StaticPosts posts = new StaticPosts();
    if (id == 1)
      return posts.getPost1();
    else if (id == 2)
      return posts.getPost2();
    return null;
  }
}
