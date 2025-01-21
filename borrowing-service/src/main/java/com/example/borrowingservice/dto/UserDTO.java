package com.example.borrowingservice.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String membershipType;
    private boolean isLocked;
    private Integer nombreMaxEmprunt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public Integer getNombreMaxEmprunt() {
        return nombreMaxEmprunt;
    }

    public void setNombreMaxEmprunt(Integer nombreMaxEmprunt) {
        this.nombreMaxEmprunt = nombreMaxEmprunt;
    }
}
