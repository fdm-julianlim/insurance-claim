package com.fdmgroup.insurance_claim.service;

import java.util.List;

import com.fdmgroup.insurance_claim.entity.Claim;

public interface ClaimService {
    Claim saveClaim(Claim claim);
    List<Claim> fetchClaimList();
    Claim updateClaim(Claim claim, Long claimId);
    void deleteClaim(Long claimId);
}