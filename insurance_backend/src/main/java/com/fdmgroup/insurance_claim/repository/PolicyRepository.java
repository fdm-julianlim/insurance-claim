package com.fdmgroup.insurance_claim.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.insurance_claim.entity.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
    List<Policy> findByClaimants_Id(Long claimantId);
    Optional<Policy> findByName(String name);
}