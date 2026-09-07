package com.fdmgroup.insurance_claim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.insurance_claim.entity.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Long>{

}