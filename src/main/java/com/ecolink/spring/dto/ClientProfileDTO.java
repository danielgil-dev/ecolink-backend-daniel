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
public class ClientProfileDTO {

    private String name;
    private Long level;
    private List<ClientMissionDTO> completedMissions;
    private List<PostRelevantDTO> listLikePost;



}
