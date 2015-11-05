package br.edu.ufrn.imd.coopuni.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "areas")
@XmlRootElement
public class Area implements Serializable {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private long id;

  private String name;

  @OneToMany(mappedBy = "area", fetch = FetchType.EAGER)
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
