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
public class MemberController {
	
  @Inject
  private FacesContext facesContext;

  @Inject
  private MemberService memberRegistration;

  private Member member;

  @Named
  @Produces
  public Member getMember() {
    return member;
  }


  public void setMember(Member member) {
    this.member = member;
  }


  public String register() throws Exception {
    try {
      memberRegistration.register(member);
      facesContext.addMessage(null,
          new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado!", "Registro feito com sucesso"));
      initNewMember();
      return "sucess";
    } catch (Exception e) {
      String errorMessage = getRootErrorMessage(e);
      FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registro sem sucesso");
      facesContext.addMessage(null, m);
    }
    return null;
  }

  @PostConstruct
  public void initNewMember() {
    member = new Member();
  }

  private String getRootErrorMessage(Exception e) {
    String errorMessage = "Registro falhou. Veja o log do servidor para mais informações";
    if (e == null) {
      return errorMessage;
    }

    Throwable t = e;
    while (t != null) {
      errorMessage = t.getLocalizedMessage();
      t = t.getCause();
    }
    return errorMessage;
  }
}
