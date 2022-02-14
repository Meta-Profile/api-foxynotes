package com.metaprofile.api.uploader.services;

import com.metaprofile.api.uploader.models.UploaderSession;

/**
 * Сессия загрузчика
 */
public interface UploaderSessionService {

    /**
     * Создает сессию загрузки
     *
     * @param userId
     * @return - id новой сессии
     */
    public UploaderSession createSession(Long userId);

}
