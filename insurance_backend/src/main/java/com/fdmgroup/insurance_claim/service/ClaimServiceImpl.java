package com.fdmgroup.insurance_claim.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fdmgroup.insurance_claim.dto.ClaimResponse;
import com.fdmgroup.insurance_claim.dto.SubmitClaimRequest;
import com.fdmgroup.insurance_claim.entity.Claim;
import com.fdmgroup.insurance_claim.entity.ClaimStatus;
import com.fdmgroup.insurance_claim.entity.Claimant;
import com.fdmgroup.insurance_claim.entity.Policy;
import com.fdmgroup.insurance_claim.repository.ClaimRepository;
import com.fdmgroup.insurance_claim.repository.ClaimantRepository;
import com.fdmgroup.insurance_claim.repository.PolicyRepository;

@Service
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;
    private final ClaimantRepository claimantRepository;
    private final PolicyRepository policyRepository;

    public ClaimServiceImpl(ClaimRepository claimRepository,
            ClaimantRepository claimantRepository, PolicyRepository policyRepository) {
        this.claimRepository = claimRepository;
        this.claimantRepository = claimantRepository;
        this.policyRepository = policyRepository;
    }

    @Override
    public Claim saveClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    @Override
    public List<Claim> fetchClaimList() {
        return claimRepository.findAll();
    }

    @Override
    public List<ClaimResponse> fetchClaimsForClaimant(Long claimantId) {
        claimantRepository.findById(claimantId)
        .orElseThrow(() -> new IllegalArgumentException("Claimant not found"));

        return claimRepository.findByClaimant_IdOrderByClaimDateDesc(claimantId)
            .stream()
            .map(claim -> ClaimResponse.retrieveClaimResponse(claim))
            .toList();
    }

    @Override
    public ClaimResponse submitClaim(Long claimantId, SubmitClaimRequest request) {
        Claimant claimant = claimantRepository.findById(claimantId)
            .orElseThrow(() -> new IllegalArgumentException("Claimant not found"));

        Policy policy = policyRepository.findById(request.getPolicyId())
            .orElseThrow(() -> new IllegalArgumentException("Policy not found"));

        boolean claimantHasPolicy = claimant.getPolicies() != null && claimant.getPolicies()
            .stream()
            .anyMatch(existingPolicy -> existingPolicy.getId() == request.getPolicyId());

        if (!claimantHasPolicy) {
            throw new IllegalArgumentException("Claimant does not have this policy");
        }

        if (!policy.isActive()
        || request.getClaimDate().isBefore(policy.getStartDate())
        || request.getClaimDate().isAfter(policy.getEndDate())) {
            throw new IllegalArgumentException("Policy does not cover the claim date");
        }

        Claim claim = new Claim();
        claim.setClaimant(claimant);
        claim.setPolicy(policy);
        claim.setClaimDate(request.getClaimDate());
        claim.setClaimAmount(request.getClaimAmount());
        claim.setDescription(request.getDescription());
        claim.setStatus(ClaimStatus.PENDING);

        return ClaimResponse.retrieveClaimResponse(claimRepository.save(claim));
    }

    @Override
    public Claim updateClaim(Claim claimDetails, Long claimId) {
        Claim claimToUpdate = claimRepository.findById(claimId).get();

        claimToUpdate.setPolicy(claimDetails.getPolicy());
        claimToUpdate.setClaimant(claimDetails.getClaimant());
        claimToUpdate.setClaimDate(claimDetails.getClaimDate());
        claimToUpdate.setClaimAmount(claimDetails.getClaimAmount());
        claimToUpdate.setStatus(claimDetails.getStatus());

        return claimRepository.save(claimToUpdate);
    }

    @Override
    public void deleteClaim(Long claimId) {
        claimRepository.deleteById(claimId);
    }
}
