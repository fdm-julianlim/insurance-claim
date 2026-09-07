package com.fdmgroup.insurance_claim.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    private ClaimService claimService;

    @PostMapping("/claims")
    private Claim saveClaim(@Valid @RequestBody Claim claim) {
        return claimService.saveClaim(claim);
    }

    @GetMapping("/claims")
    public List<Claim> fetchClaimList() {
        return claimService.fetchClaimList();
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