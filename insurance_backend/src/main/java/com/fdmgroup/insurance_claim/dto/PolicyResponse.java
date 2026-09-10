package com.fdmgroup.insurance_claim.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PolicyResponse {
    private long id;
    private String name;
    private boolean active;
    private BigDecimal coverage;
    private LocalDate startDate;
    private LocalDate endDate;

    public PolicyResponse() {
        
    }

    public PolicyResponse(long id, String name, boolean active, BigDecimal coverage,
                    LocalDate startDate, LocalDate endDate) {
            this.id = id;
            this.name = name;
            this.active = active;
            this.coverage = coverage;
            this.startDate = startDate;
            this.endDate = endDate;
    }

    public long getId() {
            return id;
    }

    public void setId(long id) {
            this.id = id;
    }

    public String getName() {
            return name;
    }

    public void setName(String name) {
            this.name = name;
    }

    public boolean isActive() {
            return active;
    }

    public void setActive(boolean active) {
            this.active = active;
    }

    public BigDecimal getCoverage() {
            return coverage;
    }

    public void setCoverage(BigDecimal coverage) {
            this.coverage = coverage;
    }

    public LocalDate getStartDate() {
            return startDate;
    }

    public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
    }

    public LocalDate getEndDate() {
            return endDate;
    }

    public void setEndDate(LocalDate endDate) {
            this.endDate = endDate;
    }
}
