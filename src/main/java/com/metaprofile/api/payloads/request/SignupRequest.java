package com.metaprofile.api.payloads.request;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

public class SignupRequest extends LoginRequest {
    @NotBlank
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
