package com.metaprofile.api.commondata.services;

import com.metaprofile.api.commondata.models.CommonData;
import com.metaprofile.api.metaprofile.models.MetaProfileField;

import java.util.List;

public interface CommonDataService {

    /**
     * Выполняет поиск по данным
     * @param query
     * @param mpfId
     * @return
     */
    List<CommonData> search(String query, Long mpfId);

    /**
     * Добавляет элемент в базу данных
     *
     * @param value
     * @param mpfId
     * @param authorId
     * @return
     */
    CommonData add(String value, Long mpfId, Long authorId);

    /**
     * Добавляет элемент в базу данных
     *
     * @param value
     * @param field
     * @param authorId
     * @return
     */
    CommonData add(String value, MetaProfileField field, Long authorId);

    /**
     * Возвращает true, если такое значение уже существует
     *
     * @param value
     * @param mpfId
     * @return
     */
    Boolean exists(String value, Long mpfId);

}
