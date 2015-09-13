package br.edu.ufrn.imd.coopuni.controller;

import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.edu.ufrn.imd.coopuni.model.Post;
import br.edu.ufrn.imd.coopuni.service.PostService;

@Model
public class PostController {
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private PostService postService;
	
	private List<Post> posts;
	
	
	public List<Post> getPosts() {
		return posts;
	}


	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	private Post post;
	
	public Post getPost() {
		return post;
	}	
	
	public void setPost(Post post) {
		this.post = post;
	}
	
	public String register() throws Exception {
	    try {
	      postService.register(post);
	      facesContext.addMessage(null,
	          new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado!", "Registro feito com sucesso"));
	      post = new Post();
	      return "sucess";
	    } catch (Exception e) {
//	      String errorMessage = getRootErrorMessage(e);
//	      FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registro sem sucesso");
//	      facesContext.addMessage(null, m);
	    }
	    return null;
	  }
	
	public List<Post> getAllPost() throws Exception {
		try {
			posts = postService.getAll();
			return posts;
		}catch( Exception e ){
//			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, 
//					"Houve um erro ao tentar buscar as publicações");
//		    facesContext.addMessage(null, m);
		}
		return null;
	}
	
	public String getType(int type){
		switch(type){
			case 1: return "Problema";
			case 2: return "Proposta";
			default: return "Não especificado";
		}		
	}
	
	public void votar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String vote = this.getVoteParam(fc);
		String post_id = this.getPostIdParam(fc);
		Post post = postService.getPostById(Integer.parseInt(post_id));
		if(vote.equals("up")) {
			post.setLikes(post.getLikes()+1);
		}
		else if (vote.equals("down")) {
			post.setDownvotes(post.getDownvotes()+1);
		}
	}
	
	
	public String getVoteParam(FacesContext fc) {
		Map<String,String> params = 
                fc.getExternalContext().getRequestParameterMap();
		return params.get("vote");
	}

	public String getPostIdParam(FacesContext fc) {
		Map<String,String> params = 
                fc.getExternalContext().getRequestParameterMap();
		return params.get("post_id");
	}
	
	
	
	
	
	

}
