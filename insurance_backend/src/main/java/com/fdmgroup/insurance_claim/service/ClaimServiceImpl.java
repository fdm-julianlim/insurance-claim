package com.fdmgroup.insurance_claim.service;

import java.util.List;

import com.fdmgroup.insurance_claim.entity.Claim;
import com.fdmgroup.insurance_claim.repository.ClaimRepository;

public class ClaimServiceImpl implements ClaimService {

    private ClaimRepository claimRepository;

    @Override
    public Claim saveClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    @Override
    public List<Claim> fetchClaimList() {
        return claimRepository.findAll();
    }

    @Override
    public Claim updateClaim(Claim claimDetails, Long claimId) {
        Claim claimToUpdate = claimRepository.findById(claimId).get();

        claimToUpdate.setPolicyName(claimDetails.getPolicyName());
        claimToUpdate.setClaimantName(claimDetails.getClaimantName());
        claimToUpdate.setClaimDate(claimDetails.getClaimDate());
        claimToUpdate.setClaimAmount(claimDetails.getClaimAmount());
        claimToUpdate.setClaimStatus(claimDetails.getClaimStatus());

        return claimRepository.save(claimToUpdate);
    }

    @Override
    public void deleteClaim(Long claimId) {
        claimRepository.deleteById(claimId);
    }
}
