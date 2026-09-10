package com.fdmgroup.insurance_claim.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.fdmgroup.insurance_claim.dto.ClaimResponse;
import com.fdmgroup.insurance_claim.dto.SubmitClaimRequest;
import com.fdmgroup.insurance_claim.entity.Claim;
import com.fdmgroup.insurance_claim.service.ClaimService;
import com.fdmgroup.insurance_claim.service.ClaimantUserService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ResponseStatusException;

@RestController 
public class ClaimController {
    private final ClaimService claimService;
    private final ClaimantUserService claimantUserService;

    public ClaimController(ClaimService claimService, ClaimantUserService claimantUserService) {
        this.claimService = claimService;
        this.claimantUserService = claimantUserService;
    }

    @PostMapping("/claims")
    private Claim saveClaim(@Valid @RequestBody Claim claim) {
        return claimService.saveClaim(claim);
    }

    @GetMapping("/claims")
    public List<Claim> fetchClaimList() {
        return claimService.fetchClaimList();
    }

    @GetMapping("/claimants/{claimantId}/claims")
    public List<ClaimResponse> fetchClaimsForClaimant(@PathVariable Long claimantId,
            Authentication authentication) {
        ensureCurrentUser(claimantId, authentication);
        return claimService.fetchClaimsForClaimant(claimantId);
    }

    @PostMapping("/claimants/{claimantId}/claims")
    @ResponseStatus(HttpStatus.CREATED)
    public ClaimResponse submitClaim(@PathVariable Long claimantId,
            @Valid @RequestBody SubmitClaimRequest request, Authentication authentication) {
        ensureCurrentUser(claimantId, authentication);
        return claimService.submitClaim(claimantId, request);
    }

    @GetMapping("/me/claims")
    public List<ClaimResponse> fetchCurrentUserClaims(Authentication authentication) {
        Long claimantId = claimantUserService.findClaimantByUsername(authentication.getName()).getId();
        return claimService.fetchClaimsForClaimant(claimantId);
    }

    @PostMapping("/me/claims")
    @ResponseStatus(HttpStatus.CREATED)
    public ClaimResponse submitCurrentUserClaim(Authentication authentication,
            @Valid @RequestBody SubmitClaimRequest request) {
        Long claimantId = claimantUserService.findClaimantByUsername(authentication.getName()).getId();
        return claimService.submitClaim(claimantId, request);
    }

    private void ensureCurrentUser(Long claimantId, Authentication authentication) {
        Long authenticatedClaimantId = claimantUserService
                .findClaimantByUsername(authentication.getName()).getId();
        if (!authenticatedClaimantId.equals(claimantId)) {
            throw new ResponseStatusException(org.springframework.http.HttpStatus.FORBIDDEN,
                    "Access denied");
        }
    }

    @PutMapping("/claims/{id}")
    public Claim updateClaim(@RequestBody Claim claim, @PathVariable("id") Long claimId) {
        return claimService.updateClaim(claim, claimId);
    }
    
    @DeleteMapping("/claims/{id}")
    public void deleteClaim(@PathVariable("id") Long claimId) {
        claimService.deleteClaim(claimId);
    }
}