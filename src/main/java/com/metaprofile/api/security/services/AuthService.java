package com.metaprofile.api.security.services;

import com.metaprofile.api.security.exceptions.SecurityAuthorizationUserUndefinedException;
import com.metaprofile.api.security.exceptions.SecurityEmailAlreadyTakenException;
import com.metaprofile.api.security.exceptions.SecurityMetaIdAlreadyTakenException;
import com.metaprofile.api.security.models.User;
import com.metaprofile.api.security.payloads.response.JwtResponse;

/**
 * Сервисы авторизации
 */
public interface AuthService {

    /**
     * Производит авторизацию пользователя и возвращает JWT токен
     *
     * @param emailOrMetaId
     * @param password
     * @return
     */
    JwtResponse signin(String emailOrMetaId, String password, String ip, String agent, String country, String fingerPrint)
            throws SecurityAuthorizationUserUndefinedException;


    /**
     * Создает нового пользователя
     *
     * @param metaId
     * @param email
     * @param password
     * @return
     */
    User signup(String metaId, String email, String password, String regIp, String regAgent, String regCountry, String regFingerPrint)
            throws SecurityEmailAlreadyTakenException, SecurityMetaIdAlreadyTakenException;

}
