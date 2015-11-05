package br.edu.ufrn.imd.coopuni.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post implements Serializable {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private long id;

  private boolean type;

  @JoinColumn(name = "category_id", referencedColumnName = "id")
  @ManyToOne
  private Category category;

  private String description;

  private int likes;

  private int downvotes;

  @Column(name = "created_at")
  @Temporal(TemporalType.DATE)
  private Date createdAt;

  @Column(name = "updated_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  @JoinColumn(name = "area_id", referencedColumnName = "id")
  @OneToOne
  private Area area;

  @OneToOne
  @PrimaryKeyJoinColumn
  private Image image;

  @OneToMany(mappedBy = "post")
  private Collection<Comment> comments;

  @JoinColumn(name = "geolocation_id", referencedColumnName = "id")
  @ManyToOne(cascade = CascadeType.ALL)
  private Geolocation geolocation;

  @JoinColumn(name = "member_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Member member;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public boolean getType() {
    return type;
  }

  public void setType(boolean type) {
    this.type = type;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

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

  public Area getArea() {
    return area;
  }

  public void setArea(Area area) {
    this.area = area;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  public Collection<Comment> getComments() {
    return comments;
  }

  public void setComments(Collection<Comment> comments) {
    this.comments = comments;
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
}
