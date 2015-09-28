package br.edu.ufrn.imd.coopuni.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.edu.ufrn.imd.coopuni.boundary.AreaDAO;
import br.edu.ufrn.imd.coopuni.model.Area;
import br.edu.ufrn.imd.coopuni.model.Geolocation;
import br.edu.ufrn.imd.coopuni.model.Member;
import br.edu.ufrn.imd.coopuni.model.Post;
import br.edu.ufrn.imd.coopuni.service.PostService;

@Model
public class PostController {

	private List<Area> areas;

	@Inject
	private FacesContext facesContext;

	@Inject
	private PostService postService;

	private Geolocation geolocation;

	private Post post;

	private List<Post> posts;

	public Geolocation getGeolocation() {
		return geolocation;
	}

	public void setGeolocation(Geolocation geolocation) {
		this.geolocation = geolocation;
	}

	public List<Area> getAreas() {		
		return areas;
	}

	public void setAreas(List<Area> areas) {		
		this.areas = areas;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@PostConstruct
	public void initNewPost() {
		post = new Post();
		geolocation = new Geolocation();
		areas = postService.getPostAreas();
	}

	public String register() throws Exception {
		try {
			post.setGeolocation(null);
			Member user = new Member();
			user.setId(2);
			user.setEmail("and@gmail.com");
			user.setPassword("586865");
			post.setMember(user);
			postService.register(post);
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado!", "Registro feito com sucesso"));
			initNewPost();
			return "success";
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registro sem sucesso");
			facesContext.addMessage(null, m);
		}
		return null;
	}

	// public void votar() {
	// FacesContext fc = FacesContext.getCurrentInstance();
	// String vote = this.getVoteParam(fc);
	// String post_id = this.getPostIdParam(fc);
	// Post post = postService.getPostById(Integer.parseInt(post_id));
	// if (vote.equals("up")) {
	// post.setLikes(post.getLikes() + 1);
	// } else if (vote.equals("down")) {
	// post.setDownvotes(post.getDownvotes() + 1);
	// }
	// }

	public String getVoteParam(FacesContext fc) {
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("vote");
	}

	public String getPostIdParam(FacesContext fc) {
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("post_id");
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
