package com.metaprofile.api.security;

/**
 * Сообщения об ошибке
 */
public class SecurityErrorMessage {

    public static final String emailIsAlreadyTaken = "Пользователь с email {0} уже зарегистрирован!";
    public static final String metaIdIsAlreadyTaken = "Пользователь с metaId {0} уже зарегистрирован!";
    public static final String roleNotFound = "Серверная ошибка. Роль {0} не найдена!";
    public static final String userAuthorizationUndefined = "Пользователь с таким логином или паролем не найден!";
    public static final String loginSessionNotFound = "Сессия авторизации не найдена!";

    public static final String userNotFound = "Пользователь не найден!";
}
