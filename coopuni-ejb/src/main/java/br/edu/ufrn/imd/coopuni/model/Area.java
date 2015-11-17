package br.edu.ufrn.imd.coopuni.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "areas")
public class Area implements Serializable {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private long id;

  private String name;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "area")
  private Collection<Geolocation> geolocations;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Collection<Geolocation> getGeolocations() {
    return geolocations;
  }

  public void setGeolocations(Collection<Geolocation> geolocations) {
    this.geolocations = geolocations;
  }
}
