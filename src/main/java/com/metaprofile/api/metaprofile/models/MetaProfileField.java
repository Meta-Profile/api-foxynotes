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
@Table(name = "meta_profile_fields")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class MetaProfileField extends LangTypeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mpf_id")
    private Long mpfId;

    @Column(name = "mpc_id")
    private Long mpcId;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private LangObject title;

    public MetaProfileField() {
    }

    public MetaProfileField(Long mpcId, LangObject title) {
        this.mpcId = mpcId;
        this.title = title;
    }

    public Long getMpcId() {
        return mpcId;
    }

    public String getTitle() {
        return title.extract(this.langType);
    }

    public void setMpcId(Long mpcId) {
        this.mpcId = mpcId;
    }

    public void setTitle(LangObject title) {
        this.title = title;
    }

    public Long getMpfId() {
        return mpfId;
    }

    public void setMpfId(Long mpfId) {
        this.mpfId = mpfId;
    }
}
