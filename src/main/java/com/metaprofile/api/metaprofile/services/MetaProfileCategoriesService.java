package com.metaprofile.api.metaprofile.services;


import com.metaprofile.api.core.LangType;
import com.metaprofile.api.metaprofile.exceptions.MetaProfileCategoryNotFoundException;
import com.metaprofile.api.metaprofile.models.MetaProfileCategory;

import java.util.List;

public interface MetaProfileCategoriesService {
    /**
     * Returns all meta profile categories
     * @return all categories
     */
    List<MetaProfileCategory> getAll(LangType lang);

    /**
     * Returns the meta profile category by id
     * @param mpcId - meta profile category id
     * @return
     * @throws MetaProfileCategoryNotFoundException
     */
    MetaProfileCategory getByMpcId(Long mpcId) throws MetaProfileCategoryNotFoundException;

    /**
     * Returns meta profile categories by query
     * @param query
     * @param lang
     * @return
     */
    List<MetaProfileCategory> findByTitleQuery(String query, LangType lang);
}
