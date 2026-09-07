package com.fdmgroup.insurance_claim.service;

import java.util.List;

import com.fdmgroup.insurance_claim.entity.Claimant;

public interface ClaimantService {
    Claimant saveClaimant(Claimant claimant);
    List<Claimant> fetchClaimantList();
    Claimant updateClaimant(Claimant claimantDetails, Long claimantId);
    void deleteClaimant(Long claimantId);
}