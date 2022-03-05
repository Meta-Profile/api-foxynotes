package com.metaprofile.api.metaprofile.errors;

public class MetaProfileErrorCode {
    /**
     * Ошибка в запросе
     */
    public static final Integer badRequest = 4000;

    /**
     * Поле не в категории
     */
    public static final Integer fieldNotInCategory = 4001;

    /**
     * Недостаточно прав для изменения данных
     */
    public static final Integer dataChangeForbidden = 4002;

    /**
     * Поле уже есть в мета-профиле
     */
    public static final Integer fieldHasAlreadyInProfile = 4003;

    /**
     * Поле еще не добавлено в мета-профиль
     */
    public static final Integer fieldNotInProfile =4005;

    // 404 errors
    public static final Integer notFoundField = 404;
    public static final Integer notFoundCategory = 404;
    public static final Integer notFoundData = 404;
    public static final Integer notFoundProfile = 404;
}
