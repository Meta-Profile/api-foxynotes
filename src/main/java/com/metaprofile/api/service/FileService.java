package com.metaprofile.api.service;

import com.metaprofile.api.exceptions.FileNotFoundException;
import com.metaprofile.api.model.File;
import com.metaprofile.api.model.enums.FileStatus;

import java.util.List;
import java.util.Optional;

public interface FileService {

    /**
     * Возвращает файл по id
     * @param fileId - id файла
     * @return
     * @throws FileNotFoundException
     */
    File getFileById(Long fileId, Long userId) throws FileNotFoundException;

    /**
     * Удаляет файл
     * @param fileId
     * @return
     * @throws FileNotFoundException
     */
    Boolean removeFile(Long fileId, Long userId) throws FileNotFoundException;

    /**
     * Обновляет статус файла
     * @param fileId
     * @param userId
     * @return
     * @throws FileNotFoundException
     */
    Boolean updateStatus(Long fileId, Long userId, FileStatus status) throws FileNotFoundException;

    /**
     * Возвращает список файлов по userId
     * @param userId
     * @return
     */
    List<File> getList(Long userId);

    /**
     * Возвращает список файлов без удаленных
     * @param userId
     * @return
     */
    List<File> getListWithoutRemoved(Long userId);
}
