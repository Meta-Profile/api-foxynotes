package com.metaprofile.api.security.models;

public enum UserRoleName {
    ROLE_USER,
    ROLE_MODER,
    ROLE_ADMIN,

    // Роли для работы с файлами
    ROLE_FILES_UPLOAD,

    // Мета профили
    ROLE_META_PROFILES_CREATE,
    ROLE_META_PROFILES_EDIT,

    // Просмотр мета профилей
    ROLE_META_PROFILES_LOCAL_VIEW,
    ROLE_META_PROFILES_GLOBAL_VIEW,
    ROLE_META_PROFILES_PUBLIC_VIEW,

    // Просмотр всех мета профилей без мета связей
    ROLE_META_PROFILES_ALL_VIEW
}