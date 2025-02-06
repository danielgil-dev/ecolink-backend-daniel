package com.ecolink.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.ChallengeDTO;
import com.ecolink.spring.dto.ChallengeFindDTO;
import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.PaginationResponse;
import com.ecolink.spring.entity.Challenge;
import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.exception.ChallengeNotFoundException;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.exception.PostNotFoundException;
import com.ecolink.spring.service.ChallengeService;
import com.ecolink.spring.service.OdsService;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/challenge")
@RequiredArgsConstructor
@RestController
public class ChallengeController {

    private final ChallengeService challengeService;
    private final DTOConverter challengeDtoConverter;
    private final OdsService odsService;

    @GetMapping
    public ResponseEntity<?> getFilteredChallenges(
            @RequestParam(required = false) List<Long> odsIds,
            @RequestParam(required = false) BigDecimal minprice,
            @RequestParam(required = false) BigDecimal maxprice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        try {
            List<Ods> odsList = (odsIds != null && !odsIds.isEmpty()) ? odsService.findAllById(odsIds) : null;

            Page<Challenge> challenges = challengeService.findByFilterAndPagination(odsList, minprice, maxprice, page,
                    size);

            if (challenges.isEmpty()) {
                throw new ChallengeNotFoundException("No se han encontrado challenges con el filtrado");
            }

            List<ChallengeDTO> dtoList = challenges.getContent().stream()
                    .map(challengeDtoConverter::converChallengeToDto)
                    .collect(Collectors.toList());

            var response = new PaginationResponse<>(
                    dtoList,
                    challenges.getNumber(),
                    challenges.getSize(),
                    challenges.getTotalElements(),
                    challenges.getTotalPages(),
                    challenges.isLast());

            return ResponseEntity.ok(response);
        } catch (ChallengeNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @GetMapping("/relevant")
    public ResponseEntity<?> getRelevantChallenges() {

        try {

            List<Challenge> challenges = challengeService.getChallengesByRelevant();
            if (challenges.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorDetails(HttpStatus.NOT_FOUND.value(), "No se encontraron post relevantes"));
            }

            List<ChallengeDTO> dtoList = challenges.stream()
                    .map(challengeDtoConverter::converChallengeToDto)
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
    public ResponseEntity<?> getChallenge(@PathVariable Long id) {
        try {
            Challenge challenge = challengeService.findById(id);
            if (challenge == null) {
                throw new ChallengeNotFoundException("No existe un challenge con id=" + id);
            }
            ChallengeFindDTO dto = challengeDtoConverter.converChallengeToChallengeFindDto(challenge);

            return ResponseEntity.ok(dto);
        } catch (ChallengeNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

}
