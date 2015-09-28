package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Member;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MemberDAO implements AbstractDAO<Long, Member> {
	@Inject
	private EntityManager em;

	@Override
	public void create(Member entity) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		entity.setPassword(
				String.format("%32x", new BigInteger(1, messageDigest.digest(entity.getPassword().getBytes()))));
		em.persist(entity);
	}

	@Override
	public void delete(Long id) {
		em.remove(em.find(Member.class, id));
	}

	@Override
	public Member retrieve(Long id) {
		return em.find(Member.class, id);
	}

	public Member retrieveByEmail(String email) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Member> c = cb.createQuery(Member.class);
		Root<Member> member = c.from(Member.class);
		c.select(member).where(cb.equal(member.get("email"), email));
		return em.createQuery(c).getSingleResult();
	}

	public Member retrieveByUsername(String username) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Member> c = cb.createQuery(Member.class);
		Root<Member> member = c.from(Member.class);
		c.select(member).where(cb.equal(member.get("username"), username));
		return em.createQuery(c).getSingleResult();
	}

	@Override
	public void update(Member entity) {
		em.merge(entity);
	}
}
