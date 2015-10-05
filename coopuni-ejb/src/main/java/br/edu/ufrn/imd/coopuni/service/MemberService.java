package br.edu.ufrn.imd.coopuni.service;

import br.edu.ufrn.imd.coopuni.boundary.MemberDAO;
import br.edu.ufrn.imd.coopuni.model.Member;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

@Stateless
public class MemberService {
  @Inject
  private Logger log;

  @Inject
  private MemberDAO memberDAO;

  public Member get(long id) {
    return memberDAO.find(id);
  }

  public boolean checkLogin(String username, String password) throws NoSuchAlgorithmException {
    return memberDAO.checkLogin(username, password);
  }

  public void register(Member member) throws Exception {
    log.info("Registrando " + member.getUsername());
    memberDAO.create(member);
  }
}
