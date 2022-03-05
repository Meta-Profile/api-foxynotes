package com.metaprofile.api.metaprofile.repositories;

import com.metaprofile.api.metaprofile.models.MetaProfileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MetaProfileDataRepository extends JpaRepository<MetaProfileData, Long> {
    @Query(
            nativeQuery = true,
            value = "SELECT mpd_id FROM meta_profile_data WHERE mp_id = :mpId AND mpf_id = :mpfId"
    )
    List<Integer> findAllByMpId(Long mpId, Long mpfId);
}
