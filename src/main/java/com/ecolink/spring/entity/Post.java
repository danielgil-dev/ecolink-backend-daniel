package com.ecolink.spring.entity;

import java.time.LocalDate;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @ManyToOne
    private Startup startup;
    @ManyToOne
    private Ods ods;
    private String title;
    private String description;
    private LocalDate postDate;

    public Post(Startup startup, Ods ods, String title, String description, LocalDate postDate){
        this.startup = startup;
        this.ods = ods;
        this.title = title;
        this.description = description;
        this.postDate = postDate;
    }
    
    @OneToMany(mappedBy = "post")
    private List<Like> likes;

    public void addLike(Like like) {
    	this.likes.add(like);
    }
}
