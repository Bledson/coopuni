package br.edu.ufrn.imd.coopuni.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;

import br.edu.ufrn.imd.coopuni.model.Area;
import br.edu.ufrn.imd.coopuni.model.Geolocation;
import br.edu.ufrn.imd.coopuni.model.Member;
import br.edu.ufrn.imd.coopuni.model.Post;
import br.edu.ufrn.imd.coopuni.service.MemberService;
import br.edu.ufrn.imd.coopuni.service.PostService;

@Model
public class PostController extends CController {

	private List<Area> areas;

	private Part img_file;

	@Inject
	private FacesContext facesContext;

	@Inject
	private PostService postService;

	@Inject
	private MemberService memberService;

	private Geolocation geolocation;

	private Post post;

	private List<Post> posts;

	private FacesContext getContext() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context;
    }

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

	public Part getImg_file() {
		return img_file;
	}

	public void setImg_file(Part img_file) {
		this.img_file = img_file;
	}

	@PostConstruct
	public void initNewPost() {
		post = new Post();
		geolocation = new Geolocation();
		areas = postService.getPostAreas();
		posts = postService.getPostsByUserId(1);
		post.setMember(memberService.retrive(1));
	}

	public String register() throws Exception {
		try {
			post.setGeolocation(null);
			upload();
			postService.register(post);			
			initNewPost();
				
			return "success";
		} catch (Exception e) {
			printErrorMsg(e, facesContext);
		}
		return null;
	}

	public String votar() {
		try {
			String vote = this.getVoteParam();
			long postId = Long.parseLong(this.getPostIdParam());		
			postService.updateVote(vote, postId);			
			refreshPage(facesContext);
			printSuccessMsg(facesContext);
			return "voted";
		}catch(Exception e) {
			printErrorMsg(e, facesContext);
		}
		return null;
	}

	private String getVoteParam() {
		Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
		return params.get("vote");
	}

	private String getPostIdParam() {
		Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
		return params.get("post_id");
	}

	/*
	 * private void upload() throws IOException {
	 * 
	 * BufferedInputStream bis = null; BufferedOutputStream bos = null; try {
	 * String filename = getFilename(img_file); File file = new
	 * File("/coopuni/images/"+filename); bis = new
	 * BufferedInputStream(img_file.getInputStream()); FileOutputStream fos =
	 * new FileOutputStream(file); bos = new BufferedOutputStream(fos); int x;
	 * while((x = bis.read())!= -1){ bos.write(x); }
	 * post.setImgPath(file.getPath()); } catch (IOException ex) {
	 * Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null,
	 * ex); } finally{ try { bos.flush(); bos.close(); bis.close(); } catch
	 * (IOException ex) {
	 * Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null,
	 * ex); } } }
	 */

	private void upload() {
		String filename = getFilename(img_file);
		post.setImgPath(filename);
	}

	private static String getFilename(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE
																													// fix.
			}
		}
		return null;
	}

}
