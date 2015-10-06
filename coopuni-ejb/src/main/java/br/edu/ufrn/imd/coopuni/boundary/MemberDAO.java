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

public class MemberDAO implements AbstractDAO<Long, Member> {
  @Inject
  private EntityManager em;

  @Override
  public void create(Member entity) throws NoSuchAlgorithmException {
    String newPassowrd = encryptPassword(entity.getPw());
    entity.setPw(newPassowrd);
    em.persist(entity);
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

  public boolean checkLogin(String username, String pw) throws NoSuchAlgorithmException {
    Member member = findByUsername(username);
    if (member != null) {
      if (checkPassword(member, pw))
        return true;
    }
    return false;
  }

  private boolean checkPassword(Member member, String pw) throws NoSuchAlgorithmException {
    String encryptedPassword = encryptPassword(pw);
    if (member.getPw().equals(encryptedPassword))
      return true;
    else
      return false;
  }

  private String encryptPassword(String pw) throws NoSuchAlgorithmException {
    MessageDigest mDigest;
    try {
      mDigest = MessageDigest.getInstance("MD5");
      byte[] valorMD5 = mDigest.digest(pw.getBytes("UTF-8"));
      StringBuffer sb = new StringBuffer();
      for (byte b : valorMD5) {
        sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
      }
      return sb.toString();
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return null;
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return null;
    }
  }

}
