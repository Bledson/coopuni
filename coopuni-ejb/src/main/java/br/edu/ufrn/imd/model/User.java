package br.edu.ufrn.imd.model;

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
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "username"}))
@XmlRootElement
public class User implements Serializable {
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

  @OneToMany(mappedBy = "user")
  private Collection<Comment> commentCollection;

  @OneToMany(mappedBy = "user")
  private Collection<Post> postCollection;
}
