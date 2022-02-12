package com.metaprofile.api.service.impl;

import com.metaprofile.api.exceptions.FileNotFoundException;
import com.metaprofile.api.model.File;
import com.metaprofile.api.model.enums.FileStatus;
import com.metaprofile.api.repository.FileRepository;
import com.metaprofile.api.service.FileService;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    /**
     * Возвращает файл по id
     *
     * @param fileId
     * @return
     */
    @Override
    public File getFileById(Long fileId, Long userId) {
        File file = fileRepository.findById(fileId).orElseThrow(FileNotFoundException::new);
        if (file.getStatus().equals(FileStatus.DELETED)) throw new FileNotFoundException();
        if (file.getStatus().equals(FileStatus.PRIVATE) && !file.getSenderId().equals(userId)) throw new FileNotFoundException();
        return file;
    }

    /**
     * Удаляет файл
     *
     * @param fileId
     * @param userId
     * @return
     * @throws FileNotFoundException
     */
    @Override
    public Boolean removeFile(Long fileId, Long userId) throws FileNotFoundException {
        File file = getFileById(fileId, userId);
        if (file.getSenderId().equals(userId)) {
            file.setStatus(FileStatus.DELETED);
            fileRepository.save(file);
            return true;
        }
        return false;
    }

    /**
     * Обновляет статус файла
     *
     * @param fileId
     * @param userId
     * @param status
     * @return
     * @throws FileNotFoundException
     */
    @Override
    public Boolean updateStatus(Long fileId, Long userId, FileStatus status) throws FileNotFoundException {
        File file = getFileById(fileId, userId);
        if (file.getSenderId().equals(userId)) {
            file.setStatus(status);
            fileRepository.save(file);
            return true;
        }
        return false;
    }
}
