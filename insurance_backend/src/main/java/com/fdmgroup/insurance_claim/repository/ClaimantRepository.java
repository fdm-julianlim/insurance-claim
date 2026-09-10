package com.fdmgroup.insurance_claim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.insurance_claim.entity.Claimant;

public interface ClaimantRepository extends JpaRepository<Claimant, Long> {
    Optional<Claimant> findByUsername(String username);
}
