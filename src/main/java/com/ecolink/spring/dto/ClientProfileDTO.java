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

    //3 post de los que le haya dado like utilizar la propiedad like que tiene el usaurio


}
