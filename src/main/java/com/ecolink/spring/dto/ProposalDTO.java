package com.ecolink.spring.dto;

import java.time.LocalDate;

import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.entity.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProposalDTO {
	private Long id;
	private ChallengeDTO challenge;
	private Startup startup;
	private String description;
	private LocalDate date;
	private Status status;
}
