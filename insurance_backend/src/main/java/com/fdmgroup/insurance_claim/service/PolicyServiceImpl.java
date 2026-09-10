package com.fdmgroup.insurance_claim.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fdmgroup.insurance_claim.dto.PolicyResponse;
import com.fdmgroup.insurance_claim.repository.ClaimantRepository;
import com.fdmgroup.insurance_claim.repository.PolicyRepository;

@Service
public class PolicyServiceImpl implements PolicyService {
    
    private final PolicyRepository policyRepository;
    private final ClaimantRepository claimantRepository;

    public PolicyServiceImpl(PolicyRepository policyRepository, ClaimantRepository claimantRepository) {
        this.policyRepository = policyRepository;
        this.claimantRepository = claimantRepository;
    }

    @Override
        public List<PolicyResponse> getPoliciesForClaimant(Long claimantId) {
        claimantRepository.findById(claimantId)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Claimant not found"));

        return policyRepository.findByClaimants_Id(claimantId)
            .stream()
            .map(policy -> new PolicyResponse(
                policy.getId(),
                policy.getName(),
                policy.isActive(),
                policy.getCoverage(),
                policy.getStartDate(),
                policy.getEndDate()))
            .toList();
    }

}
