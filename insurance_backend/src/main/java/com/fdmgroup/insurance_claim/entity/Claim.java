package com.fdmgroup.insurance_claim.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (schema = "insurance") 
public class Claim {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long claimId;

    @ManyToOne
    private Policy policy;

    private LocalDate claimDate;
    private BigDecimal claimAmount;
    private String description;
    
    @Enumerated (EnumType.STRING)
    private ClaimStatus status = ClaimStatus.PENDING;

    @ManyToOne
    private Claimant claimant;
    
    public Claim() {
    }

    public Claim(Policy policy, LocalDate claimDate, BigDecimal claimAmount, 
        String description, ClaimStatus status, Claimant claimant) {
        this.policy = policy;
        this.claimDate = claimDate;
        this.claimAmount = claimAmount;
        this.description = description;
        this.status = status;
        this.claimant = claimant;
    }

    public long getClaimId() {
        return claimId;
    }

    public void setClaimId(long claimId) {
        this.claimId = claimId;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
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

    public Claimant getClaimant() {
        return claimant;
    }

    public void setClaimant(Claimant claimant) {
        this.claimant = claimant;
    }
}
