package com.ecolink.spring.loaders;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ecolink.spring.entity.Company;
import com.ecolink.spring.entity.Like;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.service.CompanyService;
import com.ecolink.spring.service.LikeService;
import com.ecolink.spring.service.PostService;
import com.ecolink.spring.service.StartupService;

@Component
@Order(6)
public class LikeLoader implements CommandLineRunner {

    @Autowired
    private LikeService service;

    @Autowired
    private PostService postService;

    @Autowired
    private StartupService startupService;

    @Autowired
    private CompanyService companyService;

    @Override
    public void run(String... args) throws Exception {
        Post post1 = postService.findByTitle("VhAT: Apoyo emocional a través de IA");
        Post post2 = postService.findByTitle("GamingBuddy: Promoviendo la igualdad en los videojuegos");
        Company ecoSolutions = companyService.findByName("EcoSolutions");
        Company greenTech = companyService.findByName("GreenTech");
        Startup vhat = startupService.findByName("VhAT");
        Startup gamingBuddy = startupService.findByName("GamingBuddy");

        List<Like> likes = Arrays.asList(
                new Like(post1, ecoSolutions),
                new Like(post1, greenTech),
                new Like(post2, gamingBuddy),
                new Like(post2, vhat),
                new Like(post2, ecoSolutions));

        likes.forEach(like -> {
            if (!service.existsByPostAndUser(like.getPost(), like.getUser())) {
                service.save(like);
            } else {
                System.out.println("Ya existe !!!");
            }
        });
    }

}
