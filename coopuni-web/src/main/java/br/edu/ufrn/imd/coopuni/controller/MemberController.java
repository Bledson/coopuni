package br.edu.ufrn.imd.coopuni.controller;

import br.edu.ufrn.imd.coopuni.model.Member;
import br.edu.ufrn.imd.coopuni.service.MemberService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Model
public class MemberController extends CController {

  @Inject
  private FacesContext facesContext;

  @Inject
  private MemberService memberRegistration;

  @Named
  @Produces
  private Member member;


  public Member getMember() {
    return member;
  }


  public void setMember(Member member) {
    this.member = member;
  }


  public String register() throws Exception {
    try {
      memberRegistration.register(member);
      printSuccessMsg(facesContext);
      initNewMember();
      return "sucess";
    } catch (Exception e) {
      printErrorMsg(e, facesContext);
    }
    return null;
  }

  @PostConstruct
  public void initNewMember() {
    member = new Member();
  }

  
}
