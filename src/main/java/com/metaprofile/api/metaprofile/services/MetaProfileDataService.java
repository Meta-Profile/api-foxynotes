package com.metaprofile.api.metaprofile.services;

import com.metaprofile.api.metaprofile.exceptions.*;
import com.metaprofile.api.metaprofile.models.MetaProfileCategory;
import com.metaprofile.api.metaprofile.models.MetaProfileData;
import com.metaprofile.api.metaprofile.models.MetaProfileField;

public interface MetaProfileDataService {

    /**
     * Возвращает данные мета профиля
     *
     * @param mpdId
     * @return
     * @throws MetaProfileNotFoundException.DataException
     */
    MetaProfileData get(Long mpdId) throws MetaProfileNotFoundException.DataException;

    /**
     * Удаляет блок данных мета-профиля
     *
     * @param mpId
     * @param mpdId
     * @param authorId
     * @throws MetaProfileNotFoundException.DataException
     */
    Boolean remove(Long mpId, Long mpdId, Long authorId) throws MetaProfileNotFoundException.DataException;

    /**
     * Добавляет данные в мета-профиль
     *
     * @param mpId
     * @param category
     * @param field
     * @param data
     * @param authorId
     * @throws MetaProfileFieldHasAlreadyInProfileException
     */
    MetaProfileData add(Long mpId, MetaProfileCategory category, MetaProfileField field, Object data, Long authorId)
            throws MetaProfileFieldHasAlreadyInProfileException, MetaProfileFieldNotInCategoryException;

    /**
     * Добавляет данные в мета-профиль
     * @param mpId
     * @param mpcId
     * @param mpfId
     * @param data
     * @param authorId
     * @return
     * @throws MetaProfileFieldHasAlreadyInProfileException
     * @throws MetaProfileFieldNotInCategoryException
     * @throws MetaProfileNotFoundException.FieldException
     * @throws MetaProfileNotFoundException.CategoryException
     */
    MetaProfileData add(Long mpId, Long mpcId, Long mpfId, Object data, Long authorId)
            throws MetaProfileFieldHasAlreadyInProfileException, MetaProfileFieldNotInCategoryException,
            MetaProfileNotFoundException.FieldException, MetaProfileNotFoundException.CategoryException;

    /**
     * Обновляет данные мета-профиля
     *
     * @param mpId
     * @param mpdId
     * @param data
     * @param authorId
     * @return
     * @throws MetaProfileNotFoundException.DataException
     * @throws MetaProfileDataChangeForbiddenException
     */
    MetaProfileData update(Long mpId, Long mpdId, Object data, Long authorId)
            throws MetaProfileNotFoundException.DataException, MetaProfileDataChangeForbiddenException;

    /**
     * Изменяет позицию мета-поле
     *
     * @param mpId
     * @param mpdId
     * @param position
     * @param authorId
     * @return
     * @throws MetaProfileNotFoundException.DataException
     * @throws MetaProfileDataChangeForbiddenException
     */
    MetaProfileData move(Long mpId, Long mpdId, Integer position, Long authorId)
            throws MetaProfileNotFoundException.DataException, MetaProfileDataChangeForbiddenException;

}
