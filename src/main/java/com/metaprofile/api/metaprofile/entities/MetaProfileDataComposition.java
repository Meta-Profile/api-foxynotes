package com.metaprofile.api.metaprofile.entities;

import com.metaprofile.api.metaprofile.models.MetaProfileCategory;
import com.metaprofile.api.metaprofile.models.MetaProfileData;

import java.io.Serializable;
import java.util.List;

public class MetaProfileDataComposition implements Serializable {
    private MetaProfileCategory category;
    private List<MetaProfileData> fields;

    public MetaProfileDataComposition(MetaProfileCategory category, List<MetaProfileData> fields) {
        this.category = category;
        this.fields = fields;
    }

    public MetaProfileCategory getCategory() {
        return category;
    }

    public void setCategory(MetaProfileCategory category) {
        this.category = category;
    }

    public List<MetaProfileData> getFields() {
        return fields;
    }

    public void setFields(List<MetaProfileData> fields) {
        this.fields = fields;
    }
}
