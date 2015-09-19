package br.edu.ufrn.imd.coopuni.controller;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.edu.ufrn.imd.coopuni.service.CommentService;

@Model
public class CommentController {

	  @Inject
	  private FacesContext facesContext;
	  
	  @Inject
	  private CommentService commentService;
}
