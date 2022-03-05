package com.metaprofile.api.metaprofile.models;

import javax.persistence.*;

@Entity
@Table(name = "meta_profile_data")
public class MetaProfileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mpd_id")
    private Long mpdId;

    @Column
    private Long mpcId;

    @Column
    private Long mpfId;

    @Column
    private Long mpId;

    @Column
    private Long authorId;

    @Column
    private int status;

    @Column
    private int position;

    public MetaProfileData() {
    }

    public MetaProfileData(Long mpcId, Long mpfId, Long mpId, Long authorId, int status) {
        this.mpcId = mpcId;
        this.mpfId = mpfId;
        this.mpId = mpId;
        this.authorId = authorId;
        this.status = status;
    }

    public Long getMpdId() {
        return mpdId;
    }

    public void setMpdId(Long mpdId) {
        this.mpdId = mpdId;
    }

    public Long getMpcId() {
        return mpcId;
    }

    public void setMpcId(Long mpcId) {
        this.mpcId = mpcId;
    }

    public Long getMpfId() {
        return mpfId;
    }

    public void setMpfId(Long mpfId) {
        this.mpfId = mpfId;
    }

    public Long getMpId() {
        return mpId;
    }

    public void setMpId(Long mpId) {
        this.mpId = mpId;
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
}
