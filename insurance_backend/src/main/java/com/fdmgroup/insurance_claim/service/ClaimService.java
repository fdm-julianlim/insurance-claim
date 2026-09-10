package com.fdmgroup.insurance_claim.service;

import java.util.List;

import com.fdmgroup.insurance_claim.dto.ClaimResponse;
import com.fdmgroup.insurance_claim.dto.SubmitClaimRequest;
import com.fdmgroup.insurance_claim.entity.Claim;

public interface ClaimService {
    Claim saveClaim(Claim claim);
    List<Claim> fetchClaimList();
    List<ClaimResponse> fetchClaimsForClaimant(Long claimantId);
    ClaimResponse submitClaim(Long claimantId, SubmitClaimRequest request);
    Claim updateClaim(Claim claim, Long claimId);
    void deleteClaim(Long claimId);
}