package com.metaprofile.api.metaprofile.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

@Table(name = "meta_profile_data")
@Entity
public class MetaProfileData  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mpd_id")
    private Long mpdId;

    @JsonIgnore
    @Column(name = "author_id")
    private Long authorId;

    @JsonIgnore
    @Column(name = "status")
    private int status = 1;

    @Column(name = "z_position")
    private int position = 1;

    @Column(name = "mp_id")
    private Long mpId;

    @Column(name = "create_time")
    private Timestamp createTime = Timestamp.from(Instant.now());

    @Column(name = "edit_time")
    private Timestamp editTime = Timestamp.from(Instant.now());

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Object data;

    @OneToOne
    @JoinColumn(name = "mpc_id")
    private MetaProfileCategory category;

    @OneToOne
    @JoinColumn(name = "mpf_id")
    private MetaProfileField field;

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

    public Long getMpId() {
        return mpId;
    }

    public void setMpId(Long mpId) {
        this.mpId = mpId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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

    /**
     * Обновляет время редактирования
     */
    public void updateEditTime(){
        this.setEditTime(Timestamp.from(Instant.now()));
    }
}
