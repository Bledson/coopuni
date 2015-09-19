package staticdata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ufrn.imd.coopuni.model.Member;

public class StaticMembers {
	
	Member member1;
	Member member2;
	
	public StaticMembers() {
		member1 = new Member();
		member1.setEmail("member1@email.com");
		member1.setId(1);
		member1.setPassword("123");
		member1.setUsername("member1");
		member1.setCreatedAt(new Date());
		member1.setImgPath("photo2.png");
		/////////////////////////				
		member2 = new Member();
		member2.setEmail("member2@email.com");
		member2.setId(2);
		member2.setPassword("123");
		member2.setUsername("member2");
		member2.setCreatedAt(new Date());
		member2.setImgPath("photo1.png");
		
	}
	
	public List<Member> getAll() {
		List<Member> members = new ArrayList<Member>();
		members.add(member1);
		members.add(member2);
		return members;		
	}
	
	public Member getById(int id){
		if(id==1)
			return member1;
		else
			return member2;
	}
	
	
}
