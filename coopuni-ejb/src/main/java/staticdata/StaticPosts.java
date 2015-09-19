package staticdata;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufrn.imd.coopuni.model.Geolocation;
import br.edu.ufrn.imd.coopuni.model.Post;

public class StaticPosts {
	
	private final static String SALA_DE_AULA = "SALA DE AULA";
	public Post post1;
	public Post post2;
	StaticMembers members;
	private List<Post> posts;
	
	public StaticPosts() {
		posts = new ArrayList<Post>();
		members = new StaticMembers();
		post1 = new Post();
		post1.setCategory(SALA_DE_AULA);
		post1.setType("DENUNCIA");
		post1.setId(1);
		post1.setDescription("sala de aula luz com problema");
		post1.setMember(members.getById(1));
		post1.setGeolocation(new Geolocation());
		post1.setImgPath("avatar2.png");	
		///////////////
		post2= new Post();
		post2.setCategory(SALA_DE_AULA);
		post2.setType("DENUNCIA");
		post2.setId(2);
		post2.setDescription("sala de aula luz pode melhorar");
		post2.setMember(members.getById(2));
		post2.setGeolocation(new Geolocation());		
		post2.setImgPath("avatar.png");
		posts.add(post1);
		posts.add(post2);
		
	}

	public void setPosts(Post p){
		posts.add(p);
	}
	
	public List<Post> getAll(){ 				
		return posts;
	}


	public Post getPost1() {
		return post1;
	}


	public void setPost1(Post post1) {
		this.post1 = post1;
	}


	public Post getPost2() {
		return post2;
	}


	public void setPost2(Post post2) {
		this.post2 = post2;
	}
	
	
}
