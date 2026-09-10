package com.fdmgroup.insurance_claim.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.fdmgroup.insurance_claim.dto.PolicyResponse;
import com.fdmgroup.insurance_claim.service.PolicyService;
import com.fdmgroup.insurance_claim.service.ClaimantUserService;

@RestController
@RequestMapping("/claimants")
public class PolicyController {
    private final PolicyService policyService;
    private final ClaimantUserService claimantUserService;

    public PolicyController(PolicyService policyService, ClaimantUserService claimantUserService) {
        this.policyService = policyService;
        this.claimantUserService = claimantUserService;
    }

    @GetMapping("/{claimantId}/policies")
    public List<PolicyResponse> getPolicies(@PathVariable Long claimantId,
            Authentication authentication) {
        ensureCurrentUser(claimantId, authentication);
        return policyService.getPoliciesForClaimant(claimantId);
    }

    @GetMapping("/me/policies")
    public List<PolicyResponse> getCurrentUserPolicies(Authentication authentication) {
        Long claimantId = claimantUserService.findClaimantByUsername(authentication.getName()).getId();
        return policyService.getPoliciesForClaimant(claimantId);
    }

    private void ensureCurrentUser(Long claimantId, Authentication authentication) {
        if (authentication != null) {
            Long authenticatedClaimantId = claimantUserService
                    .findClaimantByUsername(authentication.getName()).getId();
            if (!authenticatedClaimantId.equals(claimantId)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
            }
        }
    }
}
