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

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController 
public class ClaimController {
    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
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
    public List<ClaimResponse> fetchClaimsForClaimant(@PathVariable Long claimantId) {
        return claimService.fetchClaimsForClaimant(claimantId);
    }

    @PostMapping("/claimants/{claimantId}/claims")
    @ResponseStatus(HttpStatus.CREATED)
    public ClaimResponse submitClaim(@PathVariable Long claimantId,
            @Valid @RequestBody SubmitClaimRequest request) {
        return claimService.submitClaim(claimantId, request);
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