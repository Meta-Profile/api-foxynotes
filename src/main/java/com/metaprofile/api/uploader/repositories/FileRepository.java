package com.metaprofile.api.uploader.repositories;

import com.metaprofile.api.uploader.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findBySenderId(Long senderId);
}
