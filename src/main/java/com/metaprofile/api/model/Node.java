package com.metaprofile.api.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "nodes")
public class Node {
    @Id
    @Column(name = "node_id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "node_name")
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
