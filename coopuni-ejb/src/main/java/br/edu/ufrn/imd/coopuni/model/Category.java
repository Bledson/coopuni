package br.edu.ufrn.imd.coopuni.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


/**
 * Created by andreza on 03/11/15.
 */
@Entity
@Table(name = "categories")
public class Category implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private Collection<Post> posts;

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

    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }
}
