package com.metaprofile.api.metaprofile.models;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "meta_profile_data")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class MetaProfileData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mpd_id")
    private Long mpdId;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "status")
    private int status;

    @Column(name = "z_position")
    private int position;

    @Column(name = "mp_id")
    private Long mpId;

    @OneToOne
    @JoinColumn(name = "mpc_id")
    private MetaProfileCategory category;

    @OneToOne
    @JoinColumn(name = "mpf_id")
    private MetaProfileField field;

    public Long getMpId() {
        return mpId;
    }

    public void setMpId(Long mpId) {
        this.mpId = mpId;
    }

    public Long getMpdId() {
        return mpdId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public MetaProfileCategory getCategory() {
        return category;
    }

    public void setCategory(MetaProfileCategory category) {
        this.category = category;
    }

    public MetaProfileField getField() {
        return field;
    }

    public void setField(MetaProfileField field) {
        this.field = field;
    }
}
