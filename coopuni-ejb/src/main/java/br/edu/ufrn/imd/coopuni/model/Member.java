package br.edu.ufrn.imd.coopuni.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "members", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "username"}))
public class Member implements Serializable {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private long id;

  @Email
  @NotEmpty
  private String email;

  @NotEmpty
  @Pattern(regexp = "[A-Za-z]*", message = "Precisa conter apenas letras")
  @Size(min = 5, max = 30)
  private String username;

  @NotEmpty
  @Pattern(regexp = "[0-9A-Za-z]*", message = "Precisa conter apenas letras e números")
  @Size(min = 5, max = 60)
  private String pw;

  private boolean admin;

  private String token;

  @Column(name = "created_at")
  @Temporal(TemporalType.DATE)
  private Date createdAt;

  @Column(name = "updated_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "member")
  private Set<Comment> comments;

  @OneToOne
  @PrimaryKeyJoinColumn
  private Image image;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "member")
  private Set<Post> posts;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPw() {
    return pw;
  }

  public void setPw(String pw) {
    this.pw = pw;
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
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

  public Set<Comment> getComments() {
    return comments;
  }

  public void setComments(Set<Comment> comments) {
    this.comments = comments;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  public Set<Post> getPosts() {
    return posts;
  }

  public void setPosts(Set<Post> posts) {
    this.posts = posts;
  }
}
