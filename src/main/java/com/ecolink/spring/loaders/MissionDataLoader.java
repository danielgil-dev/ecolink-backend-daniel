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
@Order(8)
public class MissionDataLoader implements CommandLineRunner {

    @Autowired
    private MissionService service;

    @Override
    public void run(String... args) throws Exception {
        List<Mission> missions = Arrays.asList(
                new Mission("Limpiar el parque", "Recoger basura en el parque local", MissionType.DAILY, 5),
                new Mission("Reforestar la colina", "Plantar árboles en la colina", MissionType.WEEKLY, 10),
                new Mission("Reciclar en la comunidad", "Organizar una campaña de reciclaje en la comunidad",
                        MissionType.WEEKLY, 15));

        missions.forEach(mission -> {
            if (!service.existsByNameAndType(mission.getName(), mission.getType())) {
                service.save(mission);
            }
        });
    }

}
