package com.fdmgroup.insurance_claim.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fdmgroup.insurance_claim.entity.Claim;
import com.fdmgroup.insurance_claim.entity.ClaimStatus;

public class ClaimResponse {
    private long claimId;
    private long policyId;
    private String policyName;
    private LocalDate claimDate;
    private BigDecimal claimAmount;
    private String description;
    private ClaimStatus status;

    public ClaimResponse() {

    }

    public ClaimResponse(long claimId, long policyId, String policyName,
            LocalDate claimDate, BigDecimal claimAmount, String description,
            ClaimStatus status) {
        this.claimId = claimId;
        this.policyId = policyId;
        this.policyName = policyName;
        this.claimDate = claimDate;
        this.claimAmount = claimAmount;
        this.description = description;
        this.status = status;
    }

    public static ClaimResponse retrieveClaimResponse(Claim claim) {
        return new ClaimResponse(
                claim.getClaimId(),
                claim.getPolicy().getId(),
                claim.getPolicy().getName(),
                claim.getClaimDate(),
                claim.getClaimAmount(),
                claim.getDescription(),
                claim.getStatus());
    }

    public long getClaimId() {
        return claimId;
    }

    public void setClaimId(long claimId) {
        this.claimId = claimId;
    }

    public long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(long policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public BigDecimal getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(BigDecimal claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }
}
