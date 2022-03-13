package com.metaprofile.api.security.payloads.response;

import com.metaprofile.api.uploader.models.File;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long userId;
    private String username;
    private String email;
    private String metaId;

    @Nullable
    private File avatar;

    public JwtResponse(String token, Long userId, String username, String email, File avatar) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.avatar = avatar;
        this.metaId = username;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Nullable
    public File getAvatar() {
        return avatar;
    }

    public void setAvatar(@Nullable File avatar) {
        this.avatar = avatar;
    }

    public String getMetaId() {
        return metaId;
    }

    public void setMetaId(String metaId) {
        this.metaId = metaId;
    }
}
