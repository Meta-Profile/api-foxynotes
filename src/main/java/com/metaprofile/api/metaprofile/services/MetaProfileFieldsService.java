package com.metaprofile.api.metaprofile.services;


import com.metaprofile.api.core.LangType;
import com.metaprofile.api.metaprofile.exceptions.MetaProfileNotFoundException;
import com.metaprofile.api.metaprofile.models.MetaProfileField;

import java.util.List;

public interface MetaProfileFieldsService {
    /**
     * Returns all meta profile categories
     * @return all categories
     */
    List<MetaProfileField> getAll(LangType lang);

    /**
     * Returns all meta profile categories in category
     * @return all categories
     */
    List<MetaProfileField> getByMpcId(Long mpcId, LangType lang);

    /**
     * Returns the meta profile field by id
     * @param mpfId - meta profile category id
     * @return
     * @throws MetaProfileNotFoundException.FieldException
     */
    MetaProfileField getByMpfId(Long mpfId, LangType langType) throws MetaProfileNotFoundException.FieldException;

    /**
     * Returns meta profile field by query and category
     * @param query
     * @param mpcId
     * @param lang
     * @return
     */
    List<MetaProfileField> findByTitleQuery(String query, Long mpcId, LangType lang);
}
