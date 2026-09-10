package com.fdmgroup.insurance_claim.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 
@Table (schema = "insurance")
public class Claimant {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    private String claimantName;
    private int claimantAge;
    private String claimantGender;
    
    @OneToMany (mappedBy = "claimant")
    private List<Claim> claims;

    @ManyToMany
    private List<Policy> policies;
    
    public Claimant() {
    }

    public Claimant(String claimantName, int claimantAge, String claimantGender, List<Claim> claims,
            List<Policy> policies) {
        this.claimantName = claimantName;
        this.claimantAge = claimantAge;
        this.claimantGender = claimantGender;
        this.claims = claims;
        this.policies = policies;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClaimantName() {
        return claimantName;
    }

    public void setClaimantName(String claimantName) {
        this.claimantName = claimantName;
    }

    public int getClaimantAge() {
        return claimantAge;
    }

    public void setClaimantAge(int claimantAge) {
        this.claimantAge = claimantAge;
    }

    public String getClaimantGender() {
        return claimantGender;
    }

    public void setClaimantGender(String claimantGender) {
        this.claimantGender = claimantGender;
    }

    public List<Claim> getClaims() {
        return claims;
    }

    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }

    public List<Policy> getPolicies() {
        return policies;
    }

    public void setPolicies(List<Policy> policies) {
        this.policies = policies;
    }
}
