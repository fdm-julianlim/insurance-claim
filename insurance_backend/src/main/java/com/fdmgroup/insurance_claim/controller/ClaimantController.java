package com.fdmgroup.insurance_claim.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.insurance_claim.entity.Claimant;
import com.fdmgroup.insurance_claim.service.ClaimantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/claimants")
public class ClaimantController {
    private final ClaimantService claimantService;

    public ClaimantController(ClaimantService claimantService) {
        this.claimantService = claimantService;
    }

    @PostMapping
    public Claimant saveClaimant(@Valid @RequestBody Claimant claimant) {
        return claimantService.saveClaimant(claimant);
    }

    @GetMapping
    public List<Claimant> fetchClaimantList() {
        return claimantService.fetchClaimantList();
    }

    @PutMapping("/{id}")
    public Claimant updateClaimant(@RequestBody Claimant claimant, @PathVariable("id") Long claimantId) {
        return claimantService.updateClaimant(claimant, claimantId);
    }

    @DeleteMapping("/{id}")
    public void deleteClaimant(@PathVariable("id") Long claimantId) {
        claimantService.deleteClaimant(claimantId);
    }
}