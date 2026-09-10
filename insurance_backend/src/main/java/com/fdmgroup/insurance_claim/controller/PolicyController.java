package com.fdmgroup.insurance_claim.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.insurance_claim.dto.PolicyResponse;
import com.fdmgroup.insurance_claim.service.PolicyService;

@RestController
@RequestMapping ("/claimants/{claimantId}/policies")
public class PolicyController {
    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping
    public List<PolicyResponse> getPolicies(@PathVariable Long claimantId) {
        return policyService.getPoliciesForClaimant(claimantId);
    }
}
