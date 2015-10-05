package br.edu.ufrn.imd.coopuni.controller;

import br.edu.ufrn.imd.coopuni.service.MemberService;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;

@Model
public class LoginController extends CController {

  private String username;
  private String password;

  @Inject
  private MemberService memberService;

  @Inject
  private FacesContext facesContext;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String login() throws Exception {
    try {
      boolean exist = memberService.checkLogin(username, password);
      if (exist)
        return "sucess";
      else
        facesContext.addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro!", "Senha ou usuario incorreto"));
    } catch (NoSuchAlgorithmException e) {
      printErrorMsg(e, facesContext);
    }
    return null;
  }

}
