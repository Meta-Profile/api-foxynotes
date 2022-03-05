package com.metaprofile.api.core.models;

import com.metaprofile.api.core.LangType;

import javax.persistence.Transient;

public class LangTypeModel {
    /**
     * Lang types for translation
     */
    @Transient
    protected LangType langType = LangType.ru;

    /**
     * Sets the lang type and returns current object
     * @param langType
     * @return
     */
    public LangTypeModel setLangType(LangType langType) {
        this.langType = langType;
        return this;
    }

}
