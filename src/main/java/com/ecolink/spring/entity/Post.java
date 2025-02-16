package com.ecolink.spring.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String shortDescription;
    private String imageUrl;
    private LocalDate postDate;

    @JsonIgnore
    @ManyToOne
    private Startup startup;

    @ManyToMany
    @JoinTable(name = "post_ods", joinColumns = @JoinColumn(name = "id_post"), inverseJoinColumns = @JoinColumn(name = "id_ods"))
    private List<Ods> odsList = new ArrayList<>();

    public Post(Startup startup, String title, String shortDescription, String description, LocalDate postDate) {
        this.startup = startup;
        this.title = title;
        this.description = description;
        this.shortDescription = shortDescription;
        this.postDate = postDate;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private List<Like> likes = new ArrayList<>();

    
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();
    

    public void addLike(Like like) {
        this.likes.add(like);
    }

    public void addOds(Ods ods) {
        this.odsList.add(ods);
        ods.addPost(this);
    }

    public void removeOds(Ods ods) {
        this.odsList.remove(ods);
        ods.removePost(this);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setPost(this);
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.setPost(null);
    }

    public Integer getNumberComments(){
        return this.comments.size();
    }

    public Integer getNumberLikes(){
        return this.likes.size();
    }
}
