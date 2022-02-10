package com.metaprofile.api.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
     * Имя человека
     */
    @Nullable
    @Column(name = "node_person_name")
    private String personName;

    /**
     * Фамилия
     */
    @Nullable
    @Column(name = "node_person_lastname")
    private String personLastName;

    /**
     * Отчество
     */
    @Nullable
    @Column(name = "node_person_fathername")
    private String personFatherName;

    /**
     * Возраст
     */
    @Nullable
    @Column(name = "node_age")
    private Integer age;

    /**
     * Пол
     */
    @NotNull
    @Column(name = "node_gender")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    /**
     * Статус мета профиля
     */
    @NotNull
    @Column(name = "node_status")
    @Enumerated(EnumType.ORDINAL)
    private NodeStatus status;

    @NotNull
    @Column(name = "node_author_id")
    private Integer authorId;

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

    @Nullable
    public String getPersonName() {
        return personName;
    }

    @Nullable
    public String getPersonLastName() {
        return personLastName;
    }

    @Nullable
    public String getPersonFatherName() {
        return personFatherName;
    }

    @Nullable
    public Integer getAge() {
        return age;
    }

    @NotNull
    public Gender getGender() {
        return gender;
    }

    @NotNull
    public NodeStatus getStatus() {
        return status;
    }

    @NotNull
    public Integer getAuthorId() {
        return authorId;
    }

    /**
     * Возвращает true, если у пользователя userId есть доступ к данному мета профилю
     * @param userId пользователь который запрашивает доступ
     * @return
     */
    public Boolean isAccessibleForUserId(Integer userId){
        switch (getStatus()){
            case published:
                return true;
            case local:
                return userId.equals(getAuthorId());
            case global:
                // проверка может ли он его получить
                return false;
            default:
                return false;
        }
    }
}
