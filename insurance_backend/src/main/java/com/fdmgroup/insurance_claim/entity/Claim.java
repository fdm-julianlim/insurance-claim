package com.fdmgroup.insurance_claim.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
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

    private String policyName;
    private String claimantName;
    private LocalDate claimDate;
    private String claimAmount;
    private String claimStatus;

    @ManyToOne
    private Claimant claimant;
    
    public Claim() {
    }

    public Claim(String policyName, String claimantName, LocalDate claimDate,
         String claimAmount, String claimStatus) {
        this.policyName = policyName;
        this.claimantName = claimantName;
        this.claimDate = claimDate;
        this.claimAmount = claimAmount;
        this.claimStatus = claimStatus;
    }

    public long getClaimId() {
        return claimId;
    }

    public void setClaimId(long claimId) {
        this.claimId = claimId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getClaimantName() {
        return claimantName;
    }

    public void setClaimantName(String claimantName) {
        this.claimantName = claimantName;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public String getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(String claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }
}
