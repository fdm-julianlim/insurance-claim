package com.fdmgroup.insurance_claim.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.insurance_claim.entity.Claimant;
import com.fdmgroup.insurance_claim.repository.ClaimantRepository;

@Service
public class ClaimantServiceImpl implements ClaimantService {
    private final ClaimantRepository claimantRepository;
    private final PasswordEncoder passwordEncoder;

    public ClaimantServiceImpl(ClaimantRepository claimantRepository, PasswordEncoder passwordEncoder) {
        this.claimantRepository = claimantRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Claimant saveClaimant(Claimant claimant) {
        claimant.setPassword(passwordEncoder.encode(claimant.getPassword()));
        return claimantRepository.save(claimant);
    }

    @Override
    public List<Claimant> fetchClaimantList() {
        return claimantRepository.findAll();
    }

    @Override
    public Claimant updateClaimant(Claimant claimantDetails, Long claimantId) {
        Claimant claimantToUpdate = claimantRepository.findById(claimantId).get();

        claimantToUpdate.setClaimantName(claimantDetails.getClaimantName());
        claimantToUpdate.setClaimantAge(claimantDetails.getClaimantAge());
        claimantToUpdate.setClaimantGender(claimantDetails.getClaimantGender());

        if (claimantDetails.getPassword() != null && !claimantDetails.getPassword().isBlank()) {
            claimantToUpdate.setPassword(passwordEncoder.encode(claimantDetails.getPassword()));
        }

        return claimantRepository.save(claimantToUpdate);
    }

    @Override
    public void deleteClaimant(Long claimantId) {
        claimantRepository.deleteById(claimantId);
    }
}