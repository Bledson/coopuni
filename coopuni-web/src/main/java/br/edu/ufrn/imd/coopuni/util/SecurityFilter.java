package br.edu.ufrn.imd.coopuni.util;

import br.edu.ufrn.imd.coopuni.service.MemberService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;

@RequestScoped
public class SecurityFilter {
  @Inject
  MemberService memberService;

  public boolean isUserAllowed(HttpHeaders hHeaders) {
    String user = hHeaders.getRequestHeader("user").get(0);
    String token = hHeaders.getRequestHeader("token").get(0);

    return (memberService.checkUserToken(user, token));
  }
}
