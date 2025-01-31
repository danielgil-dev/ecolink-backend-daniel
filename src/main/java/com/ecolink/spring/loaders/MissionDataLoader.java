package com.ecolink.spring.loaders;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ecolink.spring.entity.Mission;
import com.ecolink.spring.entity.MissionType;
import com.ecolink.spring.service.MissionService;

@Component
@Order(9)
public class MissionDataLoader implements CommandLineRunner {

    @Autowired
    private MissionService service;

    @Override
    public void run(String... args) throws Exception {
        List<Mission> missions = Arrays.asList(
            new Mission("Park Cleanup Crew", 
                    "Help keep your local park clean by collecting litter and properly disposing of waste.", 
                    MissionType.DAILY, 5, false),
    
            new Mission("Tree Guardians", 
                    "Join a tree-planting initiative and contribute to reforesting green areas in your city.", 
                    MissionType.WEEKLY, 10, false), 
    
            new Mission("Community Recycling Hero", 
                    "Organize or participate in a local recycling campaign to encourage proper waste management.", 
                    MissionType.WEEKLY, 15, false),
    
            new Mission("Car-Free Challenge", 
                    "Ditch your car for a week! Walk, bike, or use public transport for short trips and reduce your carbon footprint.", 
                    MissionType.WEEKLY, 80, false)
    );
    

        missions.forEach(mission -> {
            if (!service.existsByNameAndType(mission.getName(), mission.getType())) {
                service.save(mission);
            }
        });
    }

}
