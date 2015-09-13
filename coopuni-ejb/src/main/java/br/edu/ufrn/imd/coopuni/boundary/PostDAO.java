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

  @Override
  public void create(Post entity) {

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
    StaticPosts posts = new StaticPosts();
    return posts.getAll();
  }

  List<Post> getPostsByUser(Member member) {
    return null;
  }
}
