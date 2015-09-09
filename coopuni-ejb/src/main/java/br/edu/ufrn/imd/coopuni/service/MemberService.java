package br.edu.ufrn.imd.coopuni.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.edu.ufrn.imd.coopuni.boundary.MemberDAO;
import br.edu.ufrn.imd.coopuni.model.Member;

@Stateless
public class MemberService {

  @Inject
  private MemberDAO memberDAO;

  @Inject
  private Event<Member> memberEventSrc;

  @Inject
  private Logger log;

  public void register(Member member) throws Exception {
    log.info("Registering " + member.getUsername());
    memberDAO.create(member);
    memberEventSrc.fire(member);
  }
}
