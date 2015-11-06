package br.edu.ufrn.imd.coopuni.service;

import br.edu.ufrn.imd.coopuni.boundary.MemberDAO;
import br.edu.ufrn.imd.coopuni.model.Member;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;

@Stateless
public class MemberService {
  @Inject
  private Logger log;

  @Inject
  private MemberDAO memberDAO;

  public Member retrieve(long id) {
    return memberDAO.find(id);
  }

  public void register(Member member) throws Exception {
    log.info("Registrando " + member.getUsername());
    memberDAO.create(member);
  }

  public boolean checkUserToken(String username, String token) {
    Member member = memberDAO.findByUsername(username);
    return (token.compareTo(member.getToken()) == 0) ? true : false;
  }
}
