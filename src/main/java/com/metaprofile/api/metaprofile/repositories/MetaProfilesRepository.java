package com.metaprofile.api.metaprofile.repositories;

import com.metaprofile.api.metaprofile.models.MetaProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MetaProfilesRepository extends JpaRepository<MetaProfile, Long> {
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM meta_profiles WHERE mp_id = :mpId"
    )
    Optional<MetaProfile> findByMpId(Long mpId);
}
