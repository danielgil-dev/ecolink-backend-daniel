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
public class LikeDataLoader implements CommandLineRunner {

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
        Post post1 = postService.findByTitle("Revolutionizing industrial automation");
        Post post2 = postService.findByTitle("GamingBuddy: Learning through play");
        Post post3 = postService.findByTitle("Nørs: Enhancing personal health");
        Post post4 = postService.findByTitle("IM Hub: Supporting fair influencer marketing");
        
        Company ecoVision = companyService.findByName("EcoVision");
        Company greenHorizon = companyService.findByName("GreenHorizon");
        Startup vhat = startupService.findByName("VhAT");
        Startup gamingBuddy = startupService.findByName("GamingBuddy");
        
        List<Like> likes = Arrays.asList(
            new Like(post1, ecoVision), // EcoVision supports innovations in industrial automation  
            new Like(post1, greenHorizon), // GreenHorizon is interested in AI-driven automation improvements  
            new Like(post2, gamingBuddy), // GamingBuddy endorses educational gaming initiatives  
            new Like(post2, vhat), // VhAT recognizes GamingBuddy’s impact on inclusive education  
            new Like(post3, ecoVision), // EcoVision supports health-focused wearable technology  
            new Like(post4, greenHorizon) // GreenHorizon values ethical influencer marketing strategies  
    );
    
        

        likes.forEach(like -> {
            if (!service.existsByPostAndUser(like.getPost(), like.getUser())) {
                service.save(like);
            } else {
                System.out.println("Ya existe !!!");
            }
        });
    }

}
