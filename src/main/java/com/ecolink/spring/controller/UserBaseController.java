package com.ecolink.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.GetUserFrontDTO;
import com.ecolink.spring.entity.Client;
import com.ecolink.spring.entity.Company;
import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.entity.UserType;
import com.ecolink.spring.exception.ImageNotValidExtension;
import com.ecolink.spring.exception.ImageSubmitError;
import com.ecolink.spring.service.OdsService;
import com.ecolink.spring.service.UserBaseService;
import com.ecolink.spring.utils.Images;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserBaseController {
    private final UserBaseService service;
    private final OdsService odsService;
    private final DTOConverter dtoConverter;
    private final Images images;

    @Value("${spring.users.upload.dir}")
    private String uploadUserDir;

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GetUserFrontDTO> newUser(
            @RequestPart("user") String userJson,
            @RequestPart("image") MultipartFile image) {
        String urlImage = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            UserBase user = objectMapper.readValue(userJson, UserBase.class);

            List<Ods> preferences = odsService.findAllById(
                    user.getPreferences().stream()
                            .map(Ods::getId)
                            .collect(Collectors.toList()));
            user.setPreferences(preferences);
            user.setLikes(new ArrayList<>());
            user.setRegisterDate(java.time.LocalDate.now());
            user.setLevel(0L);

            if (!images.isExtensionImageValid(image)) {
                throw new ImageNotValidExtension("The extension is invalid");
            }
            urlImage = images.uploadFile(image, uploadUserDir);
            if (urlImage == null || urlImage.isEmpty()) {
                throw new ImageSubmitError("Error to submit the image");
            }
            user.setImageUrl(urlImage);

            GetUserFrontDTO dto;
            if (user instanceof Startup startup) {
                user.setUserType(UserType.STARTUP);
                startup.setProposals(new ArrayList<>());
                startup.setProducts(new ArrayList<>());
                startup.setOdsList(odsService.findAllById(
                        startup.getOdsList().stream().map(Ods::getId).collect(Collectors.toList())));
                dto = dtoConverter.convertStartupBaseToDto(startup);
            } else if (user instanceof Company company) {
                user.setUserType(UserType.COMPANY);
                dto = dtoConverter.convertCompanypBaseToDto(company);
            } else {
                Client client = (Client) user;
                user.setUserType(UserType.CLIENT);
                dto = dtoConverter.convertClientBaseToDto(client);
            }

            service.newUser(user);
            dto.setId(user.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (DataIntegrityViolationException | ImageNotValidExtension | ImageSubmitError e) {
            if (urlImage != null && !urlImage.isEmpty()) {
                images.deleteFile(urlImage, uploadUserDir);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            if (urlImage != null && !urlImage.isEmpty()) {
                images.deleteFile(urlImage, uploadUserDir);
            }
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

 
    

}
