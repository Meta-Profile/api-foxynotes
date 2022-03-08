package com.metaprofile.api.metaprofile.repositories;

import com.metaprofile.api.metaprofile.models.MetaProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MetaProfilesRepository extends JpaRepository<MetaProfile, Long> {
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM meta_profiles WHERE mp_id = :mpId"
    )
    Optional<MetaProfile> findByMpId(Long mpId);

    /**
     * Возвращает мета-профили по id автора
     * @param authorId
     * @return
     */
    List<MetaProfile> findAllByAuthorId(Long authorId);
}
