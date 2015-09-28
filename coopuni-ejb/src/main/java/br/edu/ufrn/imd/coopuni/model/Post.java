package br.edu.ufrn.imd.coopuni.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "posts")
@XmlRootElement
public class Post implements Serializable {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;

	@NotEmpty
	private String category;

	@Column(name = "post_type")
	private String type;

	@Size(max = 250)
	private String description;

	@Column(name = "created_at")
	@Temporal(TemporalType.DATE)
	private Date createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@JoinColumn(name = "geolocation_id", referencedColumnName = "id")
	@OneToOne(cascade = CascadeType.ALL)
	private Geolocation geolocation;

	@JoinColumn(name = "area_id", referencedColumnName = "id")
	@OneToOne(cascade = CascadeType.ALL)
	private Area area;
	
	@OneToMany(mappedBy = "post")
	private Collection<Comment> commentCollection;

	@JoinColumn(name = "member_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Member member;

	@Column(name = "img_path")
	private String imgPath;

	private int likes;

	private int downvotes;

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDownvotes() {
		return downvotes;
	}

	public void setDownvotes(int downvotes) {
		this.downvotes = downvotes;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Geolocation getGeolocation() {
		return geolocation;
	}

	public void setGeolocation(Geolocation geolocation) {
		this.geolocation = geolocation;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	

}
