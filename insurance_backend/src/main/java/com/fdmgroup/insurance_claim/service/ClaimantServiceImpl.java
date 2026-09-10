package com.fdmgroup.insurance_claim.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fdmgroup.insurance_claim.entity.Claimant;
import com.fdmgroup.insurance_claim.repository.ClaimantRepository;

@Service
public class ClaimantServiceImpl implements ClaimantService {
    
    private ClaimantRepository claimantRepository;

    public ClaimantServiceImpl(ClaimantRepository claimantRepository) {
        this.claimantRepository = claimantRepository;
    }

    @Override
    public Claimant saveClaimant(Claimant claimant) {
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

        return claimantRepository.save(claimantToUpdate);
    }

    @Override
    public void deleteClaimant(Long claimantId) {
        claimantRepository.deleteById(claimantId);
    }
}