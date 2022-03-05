package com.metaprofile.api.metaprofile.models;

import com.metaprofile.api.metaprofile.entities.MetaProfileDataComposition;
import com.metaprofile.api.model.LangTypeModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "meta_profiles")
public class MetaProfile extends LangTypeModel {

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

    @OneToMany
    @JoinColumn(name = "mp_id")
    private Set<MetaProfileData> data;

    public Long getMpId() {
        return mpId;
    }

    public void setMpId(Long mpId) {
        this.mpId = mpId;
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

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

//    public Set<MetaProfileData> getData() {
//        return data;
//    }

    public void setData(Set<MetaProfileData> data) {
        this.data = data;
    }

    public List<MetaProfileDataComposition> getComposition(){
        List<MetaProfileDataComposition> composition = new ArrayList<>();

        for(MetaProfileData item: data){
            MetaProfileCategory category = item.getCategory();
            category.setLangType(this.langType);
            if(composition.stream().filter(it -> it.getCategory().getMpcId().equals(category.getMpcId())).count() < 1){
                MetaProfileDataComposition com = new MetaProfileDataComposition(
                        category,
                        new ArrayList<>()
                );
                composition.add(com);
            }
            for(MetaProfileDataComposition com: composition){
                if(com.getCategory().equals(category)){
                    item.getField().setLangType(this.langType);
                    com.getFields().add(item);
                }
            }
        }

        return composition;
    }
}
