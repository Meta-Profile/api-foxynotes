package com.metaprofile.api.metaprofile.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class MetaProfileWithComposition implements Serializable {

    private Long mpId;
    private String name;
    private List<MetaProfileDataComposition> composition;

}
