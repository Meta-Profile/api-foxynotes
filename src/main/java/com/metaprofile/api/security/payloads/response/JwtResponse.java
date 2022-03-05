package com.metaprofile.api.security.payloads.response;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long userId;
    private String username;
    private String email;
    @Nullable
    private Long avatarFileId;
    private List<String> roles;

    public JwtResponse(String token, Long userId, String username, String email, List<String> roles, Long avatarFileId) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.avatarFileId = avatarFileId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAvatarFileId(@Nullable Long avatarFileId) {
        this.avatarFileId = avatarFileId;
    }
}
