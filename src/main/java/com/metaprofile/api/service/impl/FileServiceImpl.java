package com.metaprofile.api.service.impl;

import com.metaprofile.api.exceptions.FileNotFoundException;
import com.metaprofile.api.model.File;
import com.metaprofile.api.repository.FileRepository;
import com.metaprofile.api.service.FileService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    /**
     * Возвращает файл по id
     * @param fileId
     * @return
     */
    @Override
    public File getFileById(Long fileId, Long userId) {
        File file = fileRepository.findById(fileId).orElseThrow(FileNotFoundException::new);
        if(file.getStatus().equals(0)) throw new FileNotFoundException();
        return file;
    }

    @Override
    public Boolean removeFile(Long fileId, Long userId) throws FileNotFoundException {
        File file = getFileById(fileId, userId);
        if(file.getSenderId().equals(userId)){
            file.setStatus(0);
            fileRepository.save(file);
            return true;
        }
        return false;
    }
}
