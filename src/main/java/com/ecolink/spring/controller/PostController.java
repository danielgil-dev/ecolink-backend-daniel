package com.ecolink.spring.controller;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.PaginationResponse;
import com.ecolink.spring.dto.PostDTO;
import com.ecolink.spring.dto.PostItemPageDTO;
import com.ecolink.spring.dto.PostRelevantDTO;
import com.ecolink.spring.dto.PostTemplateDTO;
import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.entity.SortType;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.exception.ImageNotValidExtension;
import com.ecolink.spring.exception.ImageSubmitError;
import com.ecolink.spring.exception.PostNotFoundException;
import com.ecolink.spring.service.OdsService;
import com.ecolink.spring.service.PostService;
import com.ecolink.spring.utils.Images;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final OdsService odsService;
    private final DTOConverter postDTOConverter;
    private final Images images;


     @Value("${spring.users.upload.dir}")
    private String uploadUserDir;

    @GetMapping
    public ResponseEntity<?> getPosts(
            @RequestParam(required = false) String startupName,
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) List<Long> odsIdList,
            @RequestParam(required =  false) SortType sortLikesBy,
            @RequestParam(required =  false) SortType sortCreatedBy
            ) {

        try {

            List<Ods> odsList = new ArrayList<>();
            if (odsIdList != null && !odsIdList.isEmpty()) {
                odsIdList.forEach(odsId -> {
                    Ods ods = odsService.findById(odsId);
                    if (ods != null) {
                        odsList.add(ods);
                    }
                });
            }

            if (sortLikesBy == null) {
                sortLikesBy = SortType.DESC;
            }
            if (sortCreatedBy == null) {
                sortCreatedBy = SortType.DESC;
            }

            Page<Post> posts = postService.findByFilterAndPagination(startupName, title, odsList, page, size, sortLikesBy, sortCreatedBy);

            if (posts.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDetails(HttpStatus.NOT_FOUND.value(),
                        "No se encontraron post en la página especificada"));
            }

            List<PostItemPageDTO> dtoList = posts.getContent().stream().map(postDTOConverter::convertPostItemPageToDTO)
                    .collect(Collectors.toList());

            var response = new PaginationResponse<>(
                    dtoList,
                    posts.getNumber(),
                    posts.getSize(),
                    posts.getTotalElements(),
                    posts.getTotalPages(),
                    posts.isLast());

            return ResponseEntity.ok(response);
        } catch (PostNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }

    }

    @GetMapping("/recent")
    public ResponseEntity<?> getRecentPost() {

        try {

            List<Post> posts = postService.getRecentPost();
            if (posts.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorDetails(HttpStatus.NOT_FOUND.value(), "No se encontraron post relevantes"));
            }

            List<PostDTO> dtoList = posts.stream().map(postDTOConverter::convertPostToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        } catch (PostNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        } catch (Exception e) {

            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMethodName(@PathVariable Long id) {

        try {
            Post post = postService.findById(id);
            if (post == null) {
                throw new PostNotFoundException("No existe un post por el id " + id);
            }
            PostDTO dtoPost = postDTOConverter.convertPostToDto(post);

            return ResponseEntity.ok(dtoPost);
        } catch (PostNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @GetMapping("/relevant/{id}")
    public ResponseEntity<?> getRelevantPost(@PathVariable Long id) {

        try {
            Post post = postService.findById(id);
            if (post == null) {
                throw new PostNotFoundException("No existe un post por el id " + id);
            }
            List<Ods> odsList = post.getOdsList();
            List<Post> posts = postService.getRelevantPost(odsList, post.getId());

            if (posts.size() < 4) {
                int size = 4 - posts.size();
                if (size > 0) {
                    List<Post> otherPosts = postService.getRecentPostIngoringPosts(post.getId());
                    int index = 0;
                    while (posts.size() < 4 && index < otherPosts.size()) {
                        System.out.println("Entrado en el bucle");
                        Post otherPost = otherPosts.get(index);
                        if (!posts.contains(otherPost) && !otherPost.getId().equals(post.getId())) {
                            posts.add(otherPost);
                        }
                        index++;
                    }
                } else {
                    posts = postService.getRecentPostIngoringPosts(post.getId());
                }
            }

            List<PostRelevantDTO> dtoList = posts.stream().map(postDTOConverter::convertPostRelevantToDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(dtoList);

        } catch (PostNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        } catch (Exception e) {

            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @PostMapping( value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createPost(@AuthenticationPrincipal UserBase user, 
     @RequestPart("post") String postJson, @RequestPart("image") MultipartFile image) {
        
        System.out.println("Entamos en el controlador");
        System.out.println("Estos son los datos que me llegan " + postJson);

        String urlImage = null;
        try {
            if(!(user instanceof Startup startup)){
                throw new AccessDeniedException("Only startups can create posts");
            }
            ObjectMapper mapper = new ObjectMapper();
            PostTemplateDTO postDTO = mapper.readValue(postJson, PostTemplateDTO.class);
        
            if(postDTO.getTitle().isEmpty() || postDTO.getShortDescription().isEmpty() || postDTO.getDescription().isEmpty()){

                throw new ImageNotValidExtension("Title, short description and description are required");
            }

            // if(images.isExtensionImageValid(image)){
            //     throw new ImageSubmitError("The extension is invalid");
            // }

            // urlImage = images.uploadFile(image, uploadUserDir);
            // if(urlImage == null){
            //     throw new ImageSubmitError("Error uploading image");
            // }
            Post newPost = new Post();
            //newPost.setImageUrl(urlImage);
            newPost.setTitle(postDTO.getTitle());
            newPost.setShortDescription(postDTO.getShortDescription());
            newPost.setDescription(postDTO.getDescription());
            newPost.setStartup(startup);
            newPost.setPostDate(LocalDate.now());
            postService.save(newPost);
            return ResponseEntity.status(HttpStatus.CREATED).body(newPost);

        }catch (AccessDeniedException e){

            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.FORBIDDEN.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDetails);
        // } catch(ImageNotValidExtension | ImageSubmitError e){

        //     if(urlImage == null && !urlImage.isEmpty()){
        //         images.deleteFile(urlImage, uploadUserDir);
        //     }
        //     ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }

    }
    
}
