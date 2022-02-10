package com.metaprofile.api.service;

import com.metaprofile.api.exceptions.MetaProfileException;
import com.metaprofile.api.exceptions.NotDeniedMetaProfileException;
import com.metaprofile.api.exceptions.NotFoundMetaProfileException;
import com.metaprofile.api.model.Node;

import java.util.List;
import java.util.Optional;

public interface NodeService {

    /**
     * Возвращает все записи
     *
     * @return записи мета профилей
     */
    List<Node> getAll();

    /**
     * Возвращает мета профиль по его ID
     *
     * @param id идентификатор мета профиля
     * @return мета профиль
     */
    Optional<Node> getNodeById(Integer id);

    /**
     * Возвращает мета профиль nodeId или выбрасывает исключение, если у testUserId нет доступа к данному мета профилю
     *
     * @param nodeId
     * @param testUserId
     * @return
     * @throws NotFoundMetaProfileException  - мета профиль не найден
     * @throws NotDeniedMetaProfileException - мета профиль недоступен для testUserId
     */
    Node getNodeByIdSafe(Integer nodeId, Integer testUserId) throws NotFoundMetaProfileException, NotDeniedMetaProfileException;
}
