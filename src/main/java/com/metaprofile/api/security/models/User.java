package com.metaprofile.api.security.models;

import com.metaprofile.api.uploader.models.File;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    @Column(name = "username")
    private String username;

    @NotBlank
    @Size(max = 120)
    @Column(name = "password")
    private String password;

    @NotBlank
    @Size(max = 50)
    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "create_time")
    private Timestamp createTime = Timestamp.from(Instant.now());

    @Column(name = "edit_time")
    private Timestamp editTime = Timestamp.from(Instant.now());

    @Column(name = "mp_id")
    private Long mpId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "avatar_file_id")
    private File avatar;

    public User() {
    }
    public User(@NotNull String username, @NotNull String email, @NotNull String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @NotNull
    public Long getId() {
        return id;
    }

    @NotNull
    public String getUsername() {
        return username;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    @NotNull
    public String getEmail() {
        return email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public void setUsername(@NotNull String username) {
        this.username = username;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    public File getAvatar() {
        return avatar;
    }

    public void setAvatar(File avatar) {
        this.avatar = avatar;
    }

    public Long getMpId() {
        return mpId;
    }

    public void setMpId(Long mpId) {
        this.mpId = mpId;
    }
}
