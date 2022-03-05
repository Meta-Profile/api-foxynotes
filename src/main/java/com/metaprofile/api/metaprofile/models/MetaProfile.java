package com.metaprofile.api.metaprofile.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "meta_profiles")
public class MetaProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mp_id")
    private Long mpId;

    @Column
    private String title;

    @Column
    private Long authorId;

    @Column
    private Long typeId;

    @Column
    private Long avatarId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "meta_profile_data",
            joinColumns = @JoinColumn(name = "mp_id"),
            inverseJoinColumns = @JoinColumn(name = "mp_id"))
    private List<MetaProfileData> data = new ArrayList<>();

    public MetaProfile(){

    }

    public MetaProfile(String title, Long authorId, Long typeId, Long avatarId) {
        this.title = title;
        this.authorId = authorId;
        this.typeId = typeId;
        this.avatarId = avatarId;
    }
}
