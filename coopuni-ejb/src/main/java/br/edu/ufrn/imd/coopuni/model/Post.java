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
  private int category;

  @NotEmpty
  @Column(name = "post_type")
  private int type;

  @Size(max = 250)
  private String description;

  @Column(name = "created_at")
  @Temporal(TemporalType.DATE)
  private Date createdAt;

  @Column(name = "updated_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  @JoinColumn(name = "geolocation_id", referencedColumnName = "id")
  @OneToOne
  private Geolocation geolocation;

  @OneToMany(mappedBy = "post")
  private Collection<Comment> commentCollection;

  @JoinColumn(name = "member_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Member member;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getCategory() {
    return category;
  }

  public void setCategory(int category) {
    this.category = category;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
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


}
