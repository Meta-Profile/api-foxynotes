package com.metaprofile.api.uploader.services;

import com.metaprofile.api.uploader.exceptions.FileNotFoundException;
import com.metaprofile.api.uploader.models.File;
import com.metaprofile.api.uploader.repositories.FileRepository;
import com.metaprofile.api.uploader.enums.UploadSessionStatus;
import com.metaprofile.api.uploader.exceptions.UploaderSessionForbiddenException;
import com.metaprofile.api.uploader.exceptions.UploaderSessionNotFoundException;
import com.metaprofile.api.uploader.models.UploaderSession;
import com.metaprofile.api.uploader.repositories.UploaderSessionRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class UploaderSessionServiceImpl implements UploaderSessionService {

    UploaderSessionRepository uploaderSessionRepository;
    FileRepository fileRepository;

    public UploaderSessionServiceImpl(UploaderSessionRepository uploaderSessionRepository, FileRepository fileRepository) {
        this.uploaderSessionRepository = uploaderSessionRepository;
        this.fileRepository = fileRepository;
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

    @Override
    public UploaderSession getById(Long sessionId) throws UploaderSessionNotFoundException {
        return uploaderSessionRepository.findById(sessionId).orElseThrow(UploaderSessionNotFoundException::new);
    }

    @Override
    public UploaderSession getByIdSafe(Long sessionId, Long userId) throws UploaderSessionNotFoundException, UploaderSessionForbiddenException {
        UploaderSession session = this.getById(sessionId);
        if(!session.getUserId().equals(userId)) throw new UploaderSessionForbiddenException();
        return session;
    }

    @Override
    public UploaderSession complete(Long sessionId, Long fileId) throws UploaderSessionNotFoundException, FileNotFoundException {
        UploaderSession uploaderSession = this.getById(sessionId);
        File file = fileRepository.findById(fileId).orElseThrow(FileNotFoundException::new);
        uploaderSession.setCompleted(UploadSessionStatus.COMPLETED);
        uploaderSession.setTimeCompleted(Timestamp.from(Instant.now()));
        uploaderSession.setFileId(file.getId());
        return uploaderSessionRepository.save(uploaderSession);
    }

    @Override
    public UploaderSession setStatus(Long sessionId, UploadSessionStatus status) throws UploaderSessionNotFoundException {
        UploaderSession uploaderSession = this.getById(sessionId);
        uploaderSession.setCompleted(status);
        return uploaderSessionRepository.save(uploaderSession);
    }
}
