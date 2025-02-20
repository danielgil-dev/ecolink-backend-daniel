package com.ecolink.spring.dto;


import com.ecolink.spring.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProposalStartupProfileDTO {
	private String title;
	private String challengeTitle;
	private String description;
	private Status status;
}
