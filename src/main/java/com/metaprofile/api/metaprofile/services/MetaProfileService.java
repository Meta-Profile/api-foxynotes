package com.metaprofile.api.metaprofile.services;

import com.metaprofile.api.core.LangType;
import com.metaprofile.api.metaprofile.entities.MetaProfileWithComposition;
import com.metaprofile.api.metaprofile.exceptions.MetaProfileFieldNotFoundException;
import com.metaprofile.api.metaprofile.models.MetaProfile;

public interface MetaProfileService {

    /**
     * Возвращает мета профиль по id
     * @param id - id мета профиля
     * @param lang - язык
     * @return - мета профиль
     * @throws MetaProfileFieldNotFoundException
     */
    MetaProfile getProfileById(Long id, LangType lang) throws MetaProfileFieldNotFoundException;

}
