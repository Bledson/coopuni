package br.edu.ufrn.imd.coopuni.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.ufrn.imd.coopuni.boundary.CommentDAO;
import br.edu.ufrn.imd.coopuni.model.Comment;

@Stateless
public class CommentService {

	@Inject
	private CommentDAO commentDAO;
	
	@Inject
	private Logger log;

	public void register(Comment comment) throws Exception {	    
	    commentDAO.create(comment);	    
	}
	
}
