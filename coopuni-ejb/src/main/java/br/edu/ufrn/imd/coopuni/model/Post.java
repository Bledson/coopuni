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
}
