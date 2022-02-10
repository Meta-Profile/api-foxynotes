package com.metaprofile.api.payloads;

import java.util.HashSet;
import java.util.Set;

public class SignupRequest extends LoginRequest {
    private String email;
    private Set<String> role = new HashSet<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
