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
    private String email;
    private Long level;
    private List<MissionProfileDTO> completedMissions;
    private List<PostProfileUserDTO> listLikePost;
    List<OrderDTO> orders;
}
