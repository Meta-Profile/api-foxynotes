package com.metaprofile.api.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

/**
 * Запись мета профиля
 */
@Entity
@Table(name = "nodes")
public class Node {

    /**
     * Идентификатор записи
     */
    @Id
    @Column(name = "node_id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Название мета профиля
     */
    @NotNull
    @Column(name = "node_name")
    private String name;

    /**
     * Возвращает идентификатор мета профиля
     * @return идентификатор мета профиля
     */
    @NotNull
    public Integer getId() {
        return id;
    }

    @NotNull
    public String getName() {
        return name;
    }
}
