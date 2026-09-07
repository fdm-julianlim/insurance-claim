package com.fdmgroup.insurance_claim.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.insurance_claim.entity.Claimant;
import com.fdmgroup.insurance_claim.service.ClaimantService;

import jakarta.validation.Valid;

@RestController 
public class ClaimantController {
    private ClaimantService claimantService;

    @PostMapping("/claimants")
    public Claimant saveClaimant(@Valid @RequestBody Claimant claimant) {
        return claimantService.saveClaimant(claimant);
    }

    @GetMapping("/claimants")
    public List<Claimant> fetchClaimantList() {
        return claimantService.fetchClaimantList();
    }

    @PutMapping("/claimants/{id}")
    public Claimant updateClaimant(@RequestBody Claimant claimant, @PathVariable Long claimantId) {
        return claimantService.updateClaimant(claimant, claimantId);
    }

    @DeleteMapping("claimants/{id}")
    public void deleteClaimant(@PathVariable Long claimantId) {
        claimantService.deleteClaimant(claimantId);
    }
}