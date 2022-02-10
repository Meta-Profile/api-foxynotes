package com.metaprofile.api.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "role_id")
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private UserRoleName name;

    public Long getId() {
        return id;
    }

    public UserRoleName getName() {
        return name;
    }
}
