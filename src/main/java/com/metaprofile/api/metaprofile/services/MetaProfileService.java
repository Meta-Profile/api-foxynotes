package com.metaprofile.api.metaprofile.services;

import com.metaprofile.api.core.LangType;
import com.metaprofile.api.metaprofile.exceptions.MetaProfileNotFoundException;
import com.metaprofile.api.metaprofile.models.MetaProfile;

/**
 * Сервис для работы с мета профилями
 */
public interface MetaProfileService {

    /**
     * Возвращает мета профиль по id
     * @param id - id мета профиля
     * @param lang - язык
     * @return - мета профиль
     * @throws MetaProfileNotFoundException.FieldException
     */
    MetaProfile getProfileById(Long id, LangType lang) throws MetaProfileNotFoundException.FieldException;

    /**
     * Создает новый мета профиль
     *
     * @param title
     * @param authorId
     * @return
     */
    MetaProfile create(String title, Long authorId);

    /**
     * Удаляет мета профиль
     * @param mpId
     * @param authorId
     * @return
     */
    Boolean remove(Long mpId, Long authorId);
}
