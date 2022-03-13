package com.metaprofile.api.uploader.models;

import com.metaprofile.api.uploader.enums.UploadSessionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "upload_sessions")
public class UploaderSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @NotNull
    private Long userId;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Schema(allowableValues = {"CREATED", "COMPLETED", "WAITING", "ERROR"})
    private UploadSessionStatus completed;

    @Nullable
    private Timestamp timeStarted;

    @Nullable
    private Timestamp timeCompleted;

    @OneToOne
    @JoinColumn(name = "file_id")
    File file;

    /**
     * Пустой конструктор
     */
    public UploaderSession() {

    }

    /**
     * Создает сессию загрузчика
     *
     * @param userId
     */
    public UploaderSession(@NotNull Long userId) {
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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
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
