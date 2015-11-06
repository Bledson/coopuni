package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Member;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.security.SecureRandom;

@ApplicationScoped
public class MemberDAOImpl implements MemberDAO {
  @Inject
  private EntityManager em;

  @Override
  public void create(Member entity) throws Exception {
    entity.setToken(this.generateToken());
    em.persist(entity);
  }

  @Override
  public void delete(Member entity) throws Exception {
    em.remove(entity);
  }

  @Override
  public void delete(Long id) throws Exception {
    em.remove(em.find(Member.class, id));
  }

  @Override
  public Member find(Long id) {
    return em.find(Member.class, id);
  }

  @Override
  public Member findByEmail(String email) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Member> c = cb.createQuery(Member.class);
    Root<Member> member = c.from(Member.class);
    c.select(member).where(cb.equal(member.get("email"), email));
    return em.createQuery(c).getSingleResult();
  }

  @Override
  public Member findByUsername(String username) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Member> c = cb.createQuery(Member.class);
    Root<Member> member = c.from(Member.class);
    c.select(member).where(cb.equal(member.get("username"), username));
    return em.createQuery(c).getSingleResult();
  }

  @Override
  public void update(Member entity) throws Exception {
    em.merge(entity);
  }

  private String generateToken() {
    SecureRandom random = new SecureRandom();
    byte bytes[] = new byte[60];
    random.nextBytes(bytes);
    String token = bytes.toString();
    return token;
  }
}
