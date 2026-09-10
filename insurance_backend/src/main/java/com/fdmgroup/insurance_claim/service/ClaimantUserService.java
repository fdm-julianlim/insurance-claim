package com.fdmgroup.insurance_claim.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fdmgroup.insurance_claim.entity.Claimant;
import com.fdmgroup.insurance_claim.repository.ClaimantRepository;

@Service
public class ClaimantUserService implements UserDetailsService {
    private final ClaimantRepository claimantRepository;

    public ClaimantUserService(ClaimantRepository claimantRepository) {
        this.claimantRepository = claimantRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Claimant claimant = findClaimantByUsername(username);

        return User.withUsername(claimant.getUsername())
                .password(claimant.getPassword())
                .roles("USER")
                .build();
    }

    public Claimant findClaimantByUsername(String username) {
        return claimantRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
