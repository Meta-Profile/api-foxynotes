package com.metaprofile.api.metaprofile.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.metaprofile.api.core.LangObject;
import com.metaprofile.api.core.models.LangTypeModel;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Тип мета профиля
 */
@Entity
@Table(name = "meta_profile_types")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MetaProfileType extends LangTypeModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mpt_id")
    private Long mptId;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private LangObject title;

    @Column
    private String icon;

    public Long getMptId() {
        return mptId;
    }

    public String getTitle() {
        return title.extract(this.langType);
    }

    public void setTitle(LangObject title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
