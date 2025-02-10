package com.ecolink.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.ClientMissionDTO;
import com.ecolink.spring.dto.ClientProfileDTO;
import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.PostRelevantDTO;
import com.ecolink.spring.entity.Client;
import com.ecolink.spring.entity.ClientMission;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.exception.ClientNotFoundException;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.service.ClientMissionService;
import com.ecolink.spring.service.ClientService;
import com.ecolink.spring.service.LikeService;
import com.ecolink.spring.service.MissionService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@RequestMapping("api/client")
@RestController
public class ClientController {
    private final ClientService service;
    private final DTOConverter dtoConverter;
    private final LikeService likeService;
    private final ClientMissionService clientMissionService;

   
    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getProfileClient(@PathVariable Long id){

        try {
            
            Client client = service.findById(id);
            if (client == null) {
                throw new ClientNotFoundException("No se encontro un cliente con el id " + id);
            }

            ClientProfileDTO profileClient = new ClientProfileDTO();
            //Asiganmos el perfil y el nivel del usaurio mediante el usuario que encontramos
            profileClient.setName(client.getName());
            profileClient.setLevel(client.getLevel());

            //Obtenemos todas las missiones que ha completaado el usuario
            List<ClientMission> completedMission = clientMissionService.getClientMission(client);

            //Convertimos todas estas misiones en un DTO para hacer la vista mas sencilla 
            List<ClientMissionDTO> completedMissionDto = completedMission.stream()
            .map(clientMission -> dtoConverter.convertClientMissionDTO(clientMission.getMission(), clientMission.getCompleted()))
            .collect(Collectors.toList());
            profileClient.setCompletedMissions(completedMissionDto);

            //Obtenemos todos los post a los que un usuario le ha dado like
            List<Post> likedPostByTheUser = likeService.getPostLikedByUser(client);
            //Convertimos todos los post al DTO para mandar solo los campos que queremos
            List<PostRelevantDTO> listLikedPost = likedPostByTheUser.stream()
            .map(dtoConverter::convertPostRelevantToDTO)
            .collect(Collectors.toList());

            //Asigamos esa lista a nuestro DTO
            profileClient.setListLikePost(listLikedPost);
            return ResponseEntity.ok(profileClient);
            
        } catch (ClientNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        }catch (Exception e){
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Ocurrió un error interno en el servidor");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }
}
