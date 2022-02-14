package com.metaprofile.api.uploader.services;

import com.metaprofile.api.uploader.models.UploaderSession;
import com.metaprofile.api.uploader.repositories.UploaderSessionRepository;
import org.springframework.stereotype.Service;

@Service
public class UploaderSessionServiceImpl implements UploaderSessionService {

    UploaderSessionRepository uploaderSessionRepository;

    public UploaderSessionServiceImpl(UploaderSessionRepository uploaderSessionRepository) {
        this.uploaderSessionRepository = uploaderSessionRepository;
    }

    /**
     * Создает сессию загрузки файла
     *
     * @param userId
     * @return
     */
    @Override
    public UploaderSession createSession(Long userId) {
        UploaderSession session = new UploaderSession(userId);
        return uploaderSessionRepository.save(session);
    }
}
