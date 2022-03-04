package com.metaprofile.api.metaprofile.models;

import com.metaprofile.api.core.LangObject;
import com.metaprofile.api.model.LangTypeModel;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

/**
 *  The meta profile category
 */
@Entity
@Table(name = "meta_profile_categories")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class MetaProfileCategory extends LangTypeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mpc_id")
    private Long mpcId;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private LangObject title;

    @Column
    private String icon;


    public MetaProfileCategory() {
    }

    public MetaProfileCategory(LangObject title, String icon) {
        this.title = title;
        this.icon = icon;
    }

    public Long getMpcId() {
        return mpcId;
    }

    public String getTitle() {
        return title.extract(this.langType);
    }

    public String getIcon() {
        return icon;
    }

    public void setMpId(Long mpcId) {
        this.mpcId = mpcId;
    }

    public void setTitle(LangObject title) {
        this.title = title;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
