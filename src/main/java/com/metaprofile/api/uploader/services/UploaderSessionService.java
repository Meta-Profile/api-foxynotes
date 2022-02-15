package com.metaprofile.api.uploader.services;

import com.metaprofile.api.exceptions.FileNotFoundException;
import com.metaprofile.api.uploader.enums.UploadSessionStatus;
import com.metaprofile.api.uploader.exceptions.UploaderSessionForbiddenException;
import com.metaprofile.api.uploader.exceptions.UploaderSessionNotFoundException;
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
    UploaderSession createSession(Long userId);

    /**
     * Возвращает сессию по sessionId
     * @param sessionId
     * @return
     */
    UploaderSession getById(Long sessionId) throws UploaderSessionNotFoundException;

    /**
     * Возвращает сессию по sessionId
     * @param sessionId
     * @return
     */
    UploaderSession getByIdSafe(Long sessionId, Long userId) throws UploaderSessionNotFoundException, UploaderSessionForbiddenException;

    /**
     * Завершает сессию загрузки файла
     *
     * @param sessionId
     * @param fileId
     * @return
     * @throws UploaderSessionNotFoundException
     * @throws FileNotFoundException
     * @return
     */
    UploaderSession complete(Long sessionId, Long fileId) throws UploaderSessionNotFoundException, FileNotFoundException;

    /**
     * Обновляет статус сессии
     *
     * @param sessionId
     * @param status
     * @return
     * @throws UploaderSessionNotFoundException
     * @return
     */
    UploaderSession setStatus(Long sessionId, UploadSessionStatus status) throws UploaderSessionNotFoundException;

}
