package com.fdmgroup.insurance_claim.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class SubmitClaimRequest {
    @NotNull
    private Long policyId;

    @NotNull
    private LocalDate claimDate;

    @NotNull
    @Positive
    private BigDecimal claimAmount;

    @Size(max = 2000)
    private String description;

    public SubmitClaimRequest() {
        
    }

    public SubmitClaimRequest(Long policyId, LocalDate claimDate, 
        BigDecimal claimAmount, String description) {
            this.policyId = policyId;
            this.claimDate = claimDate;
            this.claimAmount = claimAmount;
            this.description = description;
    }

    public Long getPolicyId() {
            return policyId;
    }

    public void setPolicyId(Long policyId) {
            this.policyId = policyId;
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
}
