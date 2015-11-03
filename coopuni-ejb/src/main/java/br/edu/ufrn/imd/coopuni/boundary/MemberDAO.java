package br.edu.ufrn.imd.coopuni.boundary;

import br.edu.ufrn.imd.coopuni.model.Member;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class MemberDAO implements AbstractDAO<Long, Member> {

  @Inject
  private EntityManager em;

  @Override
  public void create(Member entity) throws NoSuchAlgorithmException {
    entity.setToken(this.generateToken());
    em.persist(entity);
  }

  private String generateToken(){
    SecureRandom random = new SecureRandom();
    byte bytes[] = new byte[60];
    random.nextBytes(bytes);
    String token = bytes.toString();
    return token;
  }

  @Override
  public void delete(Long id) {
    em.remove(em.find(Member.class, id));
  }

  @Override
  public Member find(Long id) {
    return em.find(Member.class, id);
  }

  public Member findByEmail(String email) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Member> c = cb.createQuery(Member.class);
    Root<Member> member = c.from(Member.class);
    c.select(member).where(cb.equal(member.get("email"), email));
    return em.createQuery(c).getSingleResult();
  }

  public Member findByUsername(String username) {
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


  public boolean checkUserToken(String username, String token){
    Member member = findByUsername(username);
    if(token.compareTo(member.getToken())==0){
      return true;
    } else
      return false;
  }

}
