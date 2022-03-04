package com.metaprofile.api.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LangObject implements Serializable {
    private String ru;
    private String en;

    @Transient
    public String extract(LangType langType){
        if(langType.getRaw().equals(LangType.en.getRaw()))
            return this.en;
        return this.ru;
    }
}
