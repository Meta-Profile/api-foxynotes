package com.metaprofile.api.security.services;

import com.metaprofile.api.security.exceptions.SecurityLoginSessionNotFound;
import com.metaprofile.api.security.models.UserLoginSession;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Сервис для работы с сессиями пользователя
 */
public interface UserLoginSessionsService {

    /**
     * Выполняет поиск сессии авторизации пользователя по id
     *
     * @param sessionId
     * @return
     * @throws SecurityLoginSessionNotFound
     */
    UserLoginSession get(Long sessionId) throws SecurityLoginSessionNotFound;

    /**
     * Выполняет поиск сессии авторизации пользователя по токену
     *
     * @param token
     * @return
     * @throws SecurityLoginSessionNotFound
     */
    UserLoginSession get(String token) throws SecurityLoginSessionNotFound;

    /**
     * Закрывает сессию по id
     * @param sessionId
     * @return
     */
    Boolean close(Long sessionId) throws SecurityLoginSessionNotFound;

    /**
     * Закрывает сессию по токену
     * @param token
     * @return
     */
    Boolean close(String token) throws SecurityLoginSessionNotFound;

    /**
     * Создает новую сессию авторизации
     * @param emailOrMetaId
     * @param password
     * @return
     */
    UserLoginSession create(String emailOrMetaId, String password, String ip, String agent, String country, String fingerPrint) throws UsernameNotFoundException;

}
