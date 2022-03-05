package com.metaprofile.api.commondata.models;

import com.metaprofile.api.metaprofile.models.MetaProfileField;

import javax.persistence.*;

@Table(name = "common_base")
@Entity
public class CommonData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "common_id")
    private Long commonId;

    @Column
    private String title;

    @Column
    private Long authorId;

    @OneToOne
    @JoinColumn(name = "mpf_id")
    MetaProfileField field;

    public Long getCommonId() {
        return commonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public MetaProfileField getField() {
        return field;
    }

    public void setField(MetaProfileField field) {
        this.field = field;
    }
}
