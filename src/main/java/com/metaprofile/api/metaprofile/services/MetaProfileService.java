package com.metaprofile.api.metaprofile.services;

import com.metaprofile.api.core.LangType;
import com.metaprofile.api.metaprofile.exceptions.MetaProfileFieldNotFoundException;
import com.metaprofile.api.metaprofile.models.MetaProfile;
import com.metaprofile.api.metaprofile.models.MetaProfileCategory;
import com.metaprofile.api.metaprofile.models.MetaProfileData;
import com.metaprofile.api.metaprofile.models.MetaProfileField;

public interface MetaProfileService {

    /**
     * Возвращает мета профиль по id
     * @param id - id мета профиля
     * @param lang - язык
     * @return - мета профиль
     * @throws MetaProfileFieldNotFoundException
     */
    MetaProfile getProfileById(Long id, LangType lang) throws MetaProfileFieldNotFoundException;

    /**
     * Добавляет данные для мета поля mpfId в категорию mpcId
     * @param data
     * @param authorId
     * @return
     */
    MetaProfileData addMetaData(Long mpId, MetaProfileCategory category, MetaProfileField field, Object data, Long authorId);

    /**
     * Возвращает true, если в профиль можно добавить данные
     * @param mpId
     * @param mpfId
     * @return
     */
    Boolean hasAdded(Long mpId, Long mpfId);
}
