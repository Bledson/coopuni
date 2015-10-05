package br.edu.ufrn.imd.coopuni.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table
@XmlRootElement
public class Image {
  @GeneratedValue
  @Id
  private long id;

  private String path;
}
