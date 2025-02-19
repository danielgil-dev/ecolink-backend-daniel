package com.ecolink.spring.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.CreateProposalDTO;
import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.ProposalDTO;
import com.ecolink.spring.dto.ProposalStartupDTO;
import com.ecolink.spring.entity.Challenge;
import com.ecolink.spring.entity.Client;
import com.ecolink.spring.entity.Company;
import com.ecolink.spring.entity.Proposal;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.entity.Status;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.exception.ChallengeNotFoundException;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.exception.ProposalAlredyExistsException;
import com.ecolink.spring.exception.ProposalNotFoundException;
import com.ecolink.spring.exception.ProposalNotValidException;
import com.ecolink.spring.response.SuccessDetails;
import com.ecolink.spring.service.ChallengeService;
import com.ecolink.spring.service.ProposalService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/api/proposal")
@RestController
public class ProposalController {
    private final ProposalService service;
    private final ChallengeService challengeService;
    private final DTOConverter dtoConverter;

    @GetMapping("/challenge/{id}")
    private ResponseEntity<?> getProposalsByChallenge(@AuthenticationPrincipal UserBase user, @PathVariable Long id) {
        try {
            if (user == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(),
                        "The user must be logged in");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
            }

            if (user instanceof Client) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(),
                        "The user does not have permission to view proposals");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
            }
            Challenge challenge = challengeService.findById(id);
            if (challenge == null) {
                throw new ChallengeNotFoundException("The challenge does not exist");
            }

            List<Proposal> proposals = service.findByChallenge(challenge);

            return ResponseEntity.ok(proposals);
        } catch (ChallengeNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @PostMapping("/challenge/{id}")
    private ResponseEntity<?> createProposal(@AuthenticationPrincipal UserBase user, @PathVariable Long id,
            @RequestBody CreateProposalDTO proposal) {
        try {
            if (user == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(),
                        "The user must be logged in");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
            }

            if (!(user instanceof Startup)) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(),
                        "The user does not have permission to view proposals");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
            }

            Startup startup = (Startup) user;

            if (proposal.getDescription() == null || proposal.getDescription().isEmpty()
                    || proposal.getTitle() == null) {
                throw new ProposalNotValidException("Fields are missing to create a proposal");
            }

            Challenge challenge = challengeService.findById(id);

            if (challenge == null) {
                throw new ChallengeNotFoundException("The challenge does not exist");
            }

            boolean proposalExist = service.existsByStartupAndChallenge(startup, challenge);

            if (proposalExist) {
                throw new ProposalAlredyExistsException("The user has already created a proposal");
            }

            Proposal newProposal = new Proposal(startup, challenge, proposal.getDescription(), LocalDate.now(),
                    Status.PENDING);

            newProposal.setTitle(proposal.getTitle());
            newProposal.setLink(proposal.getLink());

            service.save(newProposal);

            return ResponseEntity.ok(newProposal);
        } catch (ChallengeNotFoundException | ProposalAlredyExistsException | ProposalNotValidException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateProposal(@AuthenticationPrincipal UserBase user, @PathVariable Long id,
            @RequestBody CreateProposalDTO proposal) {
        try {
            if (user == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(),
                        "The user must be logged in");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
            }

            if (!(user instanceof Startup)) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(),
                        "The user does not have permission to view proposals");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
            }

            Startup startup = (Startup) user;

            boolean proposalExist = service.existsByIdAndStartup(id, startup);

            if (!proposalExist) {
                throw new ProposalNotFoundException("The user has not created a proposal");
            }

            Proposal userProposal = service.findByIdAndStartup(id, startup);

            if (proposal.getTitle() != null) {
                userProposal.setTitle(proposal.getTitle());
            }

            if (proposal.getDescription() != null) {
                userProposal.setDescription(proposal.getDescription());
            }

            if (proposal.getLink() != null) {
                userProposal.setLink(proposal.getLink());
            }

            service.save(userProposal);

            return ResponseEntity.ok(userProposal);
        } catch (ChallengeNotFoundException | ProposalNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteProposal(@AuthenticationPrincipal UserBase user, @PathVariable Long id) {
        try {
            if (user == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(),
                        "The user must be logged in");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
            }

            if (!(user instanceof Startup)) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(),
                        "The user does not have permission to view proposals");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
            }

            Startup startup = (Startup) user;

            boolean proposalExist = service.existsByIdAndStartup(id, startup);

            if (!proposalExist) {
                throw new ProposalNotFoundException("The user has not created a proposal");
            }

            Proposal userProposal = service.findByIdAndStartup(id, startup);

            service.delete(userProposal);

            SuccessDetails successDetails = new SuccessDetails(HttpStatus.OK.value(), "Proposal deleted successfully");
            return ResponseEntity.ok(successDetails);
        } catch (ChallengeNotFoundException | ProposalNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @GetMapping
    public ResponseEntity<?> findByStartup(@AuthenticationPrincipal UserBase user) {
        if (user == null) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(),
                    "The user must be logged in");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
        }

        if (user instanceof Startup startup) {
            List<Proposal> proposals = service.findByStartup(startup);
            List<ProposalStartupDTO> proposalsDTO = proposals.stream().map(dtoConverter::convertProposalStartupToDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(proposalsDTO);
        } else if(user instanceof Company company){
            
        }

        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(),
                "The user does not have permission to view proposals");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

}
