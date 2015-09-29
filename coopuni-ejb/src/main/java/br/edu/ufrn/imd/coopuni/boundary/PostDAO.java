package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Member;
import br.edu.ufrn.imd.coopuni.model.Post;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import java.util.List;
import java.util.Set;

public class PostDAO implements AbstractDAO<Long, Post> {

	@Inject
	private EntityManager em;

	@Override
	public void create(Post entity) {
		em.persist(entity);
	}

	@Override
	public void delete(Long id) {

	}

	@Override
	public Post retrieve(Long id) {
		return null;
	}

	@Override
	public void update(Post entity) {

	}

	public List<Post> getPostsByUser(Member member) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Post> c = cb.createQuery(Post.class);
		Root<Post> post = c.from(Post.class);
		c.select(post).where(cb.equal(post.get("member").get("id"), member.getId()));
		return em.createQuery(c).getResultList();
	}

}
