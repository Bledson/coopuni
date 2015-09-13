package br.edu.ufrn.imd.coopuni.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "members", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "username"}))
@XmlRootElement
public class Member implements Serializable {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private long id;

  @Email
  @NotEmpty
  private String email;

  @NotEmpty
  @Pattern(regexp = "[A-Za-z ]*", message = "Precisa conter apenas letras e espaços")
  @Size(min = 5, max = 30)
  private String username;

  @NotEmpty
  @Pattern(regexp = "[0-9A-Za-z]*", message = "Precisa conter apenas letras e espaços")
  @Size(min = 5, max = 60)
  private String password;

  @Column(name = "created_at")
  @Temporal(TemporalType.DATE)
  private Date createdAt;

  @Column(name = "updated_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  @OneToMany(mappedBy = "member")
  private Collection<Comment> commentCollection;

  @OneToMany(mappedBy = "member")
  private Collection<Post> postCollection;
  
  @Column(name = "img_path")
  private String imgPath;
  
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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
}
