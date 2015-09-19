package br.edu.ufrn.imd.coopuni.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "geolocations")
@XmlRootElement
public class Geolocation implements Serializable {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private long id;

  @Digits(fraction = 6, integer = 10)
  @NotEmpty
  private float latitude;

  @Digits(fraction = 6, integer = 10)
  @NotEmpty
  private float longitude;

  @Column(name = "created_at")
  @Temporal(TemporalType.DATE)
  private Date createdAt;

  @Column(name = "updated_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  @OneToOne(mappedBy = "geolocation")
  private Post post;
  
  private String descricao;

public float getLatitude() {
	return latitude;
}

public void setLatitude(float latitude) {
	this.latitude = latitude;
}

public float getLongitude() {
	return longitude;
}

public void setLongitude(float longitude) {
	this.longitude = longitude;
}

public String getDescricao() {
	return descricao;
}

public void setDescricao(String descricao) {
	this.descricao = descricao;
}
  
  
}
