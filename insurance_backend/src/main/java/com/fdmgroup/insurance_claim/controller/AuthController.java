package com.fdmgroup.insurance_claim.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.insurance_claim.dto.CurrentUserResponse;
import com.fdmgroup.insurance_claim.dto.LoginRequest;
import com.fdmgroup.insurance_claim.dto.LoginResponse;
import com.fdmgroup.insurance_claim.entity.Claimant;
import com.fdmgroup.insurance_claim.security.JwtService;
import com.fdmgroup.insurance_claim.service.ClaimantUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final ClaimantUserService claimantUserService;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager,
            ClaimantUserService claimantUserService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.claimantUserService = claimantUserService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()));
        Claimant claimant = claimantUserService.findClaimantByUsername(authentication.getName());

        return new LoginResponse(jwtService.generateToken(claimant), claimant.getClaimantName());
    }

    @GetMapping("/me")
    public CurrentUserResponse currentUser(Authentication authentication) {
        Claimant claimant = claimantUserService.findClaimantByUsername(authentication.getName());
        return new CurrentUserResponse(
                claimant.getId(), claimant.getUsername(), claimant.getClaimantName());
    }
}