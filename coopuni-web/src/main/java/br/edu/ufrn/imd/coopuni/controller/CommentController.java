package br.edu.ufrn.imd.coopuni.controller;

import br.edu.ufrn.imd.coopuni.service.CommentService;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Model
public class CommentController {

  @Inject
  private FacesContext facesContext;

  @Inject
  private CommentService commentService;
}
