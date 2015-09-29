package br.edu.ufrn.imd.coopuni.controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class CController {

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
	
	public void printErrorMsg(Exception e, FacesContext fc) {
		String errorMessage = getRootErrorMessage(e);
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registro sem sucesso");
		fc.addMessage(null, m);
	}
	
	public void printSuccessMsg(FacesContext fc){
		fc.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado!", "Registro feito com sucesso"));
	}
	
	public void refreshPage(FacesContext fc) throws IOException {
		ExternalContext ec = fc.getExternalContext();
	    ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		}

}
