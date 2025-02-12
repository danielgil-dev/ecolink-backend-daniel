package com.ecolink.spring.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
<<<<<<<< HEAD:src/main/java/com/ecolink/spring/dto/StartupPublicProfileDTO.java
public class StartupPublicProfileDTO {
========
public class StartupProfile {
>>>>>>>> d131b4cd19faf8ddfecda7fd8ef04b924a55c0e0:src/main/java/com/ecolink/spring/dto/StartupProfile.java

    String imageStartup;
    String status;
    String name;
    String description;
    List<OdsWithoutIdDTO> odsList;
    List<ProposalStartupProfileDTO> proposals;
    List<StartupProductPublicProfileDTO> products;
    List<PostProfileUserDTO> listLikePost;
}
