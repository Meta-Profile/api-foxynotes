package com.metaprofile.api.uploader.models;

import com.metaprofile.api.uploader.enums.UploadSessionStatus;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "upload_sessions")
public class UploaderSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @Nullable
    private Long fileId;

    @NotBlank
    private Long userId;

    @NotBlank
    @Enumerated(EnumType.ORDINAL)
    private UploadSessionStatus completed = UploadSessionStatus.CREATED;

    @Nullable
    private Timestamp timeStarted;

    @Nullable
    private Timestamp timeCompleted;

    /**
     * Пустой конструктор
     */
    public UploaderSession(){

    }

    /**
     * Создает сессию загрузчика
     * @param userId
     */
    public UploaderSession(Long userId){
        this.userId = userId;
        this.timeStarted = Timestamp.from(Instant.now());
        this.completed = UploadSessionStatus.CREATED;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    @Nullable
    public Long getFileId() {
        return fileId;
    }

    public void setFileId(@Nullable Long fileId) {
        this.fileId = fileId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UploadSessionStatus getCompleted() {
        return completed;
    }

    public void setCompleted(UploadSessionStatus completed) {
        this.completed = completed;
    }

    @Nullable
    public Timestamp getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(@Nullable Timestamp timeStarted) {
        this.timeStarted = timeStarted;
    }

    @Nullable
    public Timestamp getTimeCompleted() {
        return timeCompleted;
    }

    public void setTimeCompleted(@Nullable Timestamp timeCompleted) {
        this.timeCompleted = timeCompleted;
    }
}
