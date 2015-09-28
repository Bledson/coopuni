package br.edu.ufrn.imd.coopuni.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "areas")
@XmlRootElement
public class Area implements Serializable {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	 @Override
	    public boolean equals(Object other) {
	        return (other != null && getClass() == other.getClass() && id != null)
	            ? id.equals(((Area) other).id)
	            : (other == this);
	    }

	    @Override
	    public int hashCode() {
	        return (id != null) 
	            ? (getClass().hashCode() + id.hashCode())
	            : super.hashCode();
	    }

	
	
	
	
}
