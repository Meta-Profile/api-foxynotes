package com.metaprofile.api.uploader.enums;

/**
 * Статус файла
 */
public enum FileStatus {
    /**
     * 0 - файл удален
     */
    DELETED,

    /**
     * 1 - файл доступен только автору
     */
    PRIVATE,

    /**
     * 2 - файл доступен всем
     */
    PUBLIC,
}
