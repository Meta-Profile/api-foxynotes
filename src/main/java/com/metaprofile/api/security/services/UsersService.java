package com.metaprofile.api.security.services;

import com.metaprofile.api.security.models.User;

public interface UsersService {

    /**
     * Возвращает пользователя по id
     *
     * @param userId
     * @return
     */
    User getUser(Long userId);
}
