package com.fdmgroup.insurance_claim.service;

import java.util.List;

import com.fdmgroup.insurance_claim.dto.PolicyResponse;

public interface PolicyService {
    List<PolicyResponse> getPoliciesForClaimant(Long claimantId);
}
