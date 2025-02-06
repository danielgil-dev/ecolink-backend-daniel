package com.ecolink.spring.loaders;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ecolink.spring.entity.Category;
import com.ecolink.spring.service.CategoryService;

@Component
@Order(3)
public class CategoryDataLoader implements CommandLineRunner {
    @Autowired
    private CategoryService service;

    @Override
    public void run(String... args) throws Exception {
        List<Category> categories = Arrays.asList(
                new Category("Electronics & Technology"),
                new Category("Fashion & Accessories"),
                new Category("Beauty & Personal Care"),
                new Category("Home & Kitchen"),
                new Category("Grocery & Food"),
                new Category("Sports & Outdoors"),
                new Category("Toys & Games"),
                new Category("Health & Wellness"),
                new Category("Automotive & Accessories"),
                new Category("Tools & Home Improvement"),
                new Category("Furniture & Décor"),
                new Category("Books & Stationery"),
                new Category("Pet Supplies"),
                new Category("Industrial & Office Supplies"),
                new Category("Baby & Kids"),
                new Category("Video Games"),
                new Category("Music, Movies & Entertainment"));

        categories.forEach(category -> {
            if (!service.existsByName(category.getName())) {
                service.save(category);
            }
        });
    }
}
