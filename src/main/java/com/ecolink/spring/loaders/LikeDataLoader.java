package com.ecolink.spring.loaders;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ecolink.spring.entity.Client;
import com.ecolink.spring.entity.Company;
import com.ecolink.spring.entity.Like;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.service.ClientService;
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

    @Autowired
    private ClientService clientService;

    @Override
    public void run(String... args) throws Exception {
        List<Post> postList = postService.getAllPosts();
        List<UserBase> possibleLikers = new ArrayList<>();
        List<Company> companyList = companyService.getAllCompanies();
        possibleLikers.addAll(companyList);
        List<Client> clientList = clientService.getAllClients();
        possibleLikers.addAll(clientList);
        List<Startup> startupList = startupService.findAll();
        possibleLikers.addAll(startupList);
        Random random = new Random();

        for(Post post : postList){

            int index = random.nextInt(possibleLikers.size());
            UserBase randomLiker = possibleLikers.get(index);

            if ( post != null && randomLiker != null && !service.existsByPostAndUser(post, randomLiker)) {
                Like like = new Like(post, randomLiker);
                service.save(like);
            } else {
                System.out.println("Ya existe !!!");
            }
        }
    
    }

}
