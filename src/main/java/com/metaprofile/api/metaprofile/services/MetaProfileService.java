package com.metaprofile.api.metaprofile.services;

import com.metaprofile.api.core.LangType;
import com.metaprofile.api.metaprofile.exceptions.MetaProfileNotFoundException;
import com.metaprofile.api.metaprofile.models.MetaProfile;
import com.metaprofile.api.metaprofile.payloads.MetaProfileUpdatePayload;

import java.util.List;
import java.util.Optional;

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
    MetaProfile create(String title, String color, Long authorId);

    /**
     * Обновляет мета профиль
     *
     * @param mpId
     * @param authorId
     * @return
     */
    MetaProfile update(Long mpId, Long authorId, MetaProfileUpdatePayload payload);

    /**
     * Удаляет мета профиль
     * @param mpId
     * @param authorId
     * @return
     */
    Boolean remove(Long mpId, Long authorId);

    /**
     * Листинг мета профилей
     *
     * @param authorId
     * @param lang
     * @return
     */
    List<MetaProfile> list(Long authorId, LangType lang);
}
