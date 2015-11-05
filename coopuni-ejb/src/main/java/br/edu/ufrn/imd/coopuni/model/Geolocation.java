package br.edu.ufrn.imd.coopuni.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "geolocations")
@XmlRootElement
public class Geolocation implements Serializable {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private long id;

  @Digits(fraction = 6, integer = 10)
  @NotNull
  private Float latitude;

  @Digits(fraction = 6, integer = 10)
  @NotNull
  private Float longitude;

  @Column(name = "created_at")
  @Temporal(TemporalType.DATE)
  private Date createdAt;

  @Column(name = "updated_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  @JoinColumn(name = "area_id", referencedColumnName = "id")
  @ManyToOne
  private Area area;

  @OneToMany(mappedBy = "geolocation")
  private Collection<Post> post;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Float getLatitude() {
    return latitude;
  }

  public void setLatitude(Float latitude) {
    this.latitude = latitude;
  }

  public Float getLongitude() {
    return longitude;
  }

  public void setLongitude(Float longitude) {
    this.longitude = longitude;
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


  public Collection<Post> getPost() {
    return post;
  }

  public void setPost(Collection<Post> post) {
    this.post = post;
  }
}
