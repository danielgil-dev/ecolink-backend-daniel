package com.ecolink.spring.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.ClientProfileDTO;
import com.ecolink.spring.dto.CompanyProfileDTO;
import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.MissionProfileDTO;
import com.ecolink.spring.dto.PostProfileUserDTO;
import com.ecolink.spring.dto.StartupPrivateProfileDTO;
import com.ecolink.spring.entity.Client;
import com.ecolink.spring.entity.ClientMission;
import com.ecolink.spring.entity.Company;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.exception.CompanyNotFoundException;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.service.ClientMissionService;
import com.ecolink.spring.service.LikeService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("api/profile")
@RestController
public class ProfileController {

    private final ClientMissionService clientMissionService;
    private final DTOConverter dtoConverter;
    private final LikeService likeService;

    @GetMapping
    @Transactional
    public ResponseEntity<?> getProfileUser(@AuthenticationPrincipal UserBase user) {

        if (user instanceof Client) {
            Client client = (Client) user;
            ClientProfileDTO profileClient = new ClientProfileDTO();
            // Asiganmos el perfil y el nivel del usaurio mediante el usuario que
            // encontramos
            profileClient.setName(client.getName());
            profileClient.setLevel(client.getLevel());
            profileClient.setEmail(client.getEmail());

            // Obtenemos todas las missiones que ha completaado el usuario
            List<ClientMission> completedMission = clientMissionService.getClientMission(client);

            // Convertimos todas estas misiones en un DTO para hacer la vista mas sencilla
            List<MissionProfileDTO> completedMissionDto = completedMission.stream()
                    .map(clientMission -> dtoConverter.convertMissionToMissionProfileDTO(clientMission.getMission()))
                    .collect(Collectors.toList());
            profileClient.setCompletedMissions(completedMissionDto);

            // Obtenemos todos los post a los que un usuario le ha dado like
            List<Post> likedPostByTheUser = likeService.getPostLikedByUser(client);
            // Convertimos todos los post al DTO para mandar solo los campos que queremos
            List<PostProfileUserDTO> listLikedPost = likedPostByTheUser.stream()
                    .map(dtoConverter::convertPostToPostProfileDto)
                    .collect(Collectors.toList());

            // Asigamos esa lista a nuestro DTO
            profileClient.setListLikePost(listLikedPost);
            return ResponseEntity.ok(profileClient);

        } else if (user instanceof Startup startup) {
            System.out.println("Es una startup el nombre es" + startup.getName());
            StartupPrivateProfileDTO dto = dtoConverter.convertStartupToStartupPrivateProfile(startup);
            return ResponseEntity.ok(dto);

        } else if (user instanceof Company company) {
            CompanyProfileDTO dtoCompany = dtoConverter.convetCompanyToCompanyProfileDTO(company);
            return ResponseEntity.ok(dtoCompany);

        }
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
