package com.metaprofile.api.uploader.repositories;

import com.metaprofile.api.uploader.models.UploaderSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploaderSessionRepository extends JpaRepository<UploaderSession, Long> {
}
