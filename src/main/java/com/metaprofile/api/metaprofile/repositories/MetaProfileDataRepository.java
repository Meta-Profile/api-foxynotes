package com.metaprofile.api.metaprofile.repositories;

import com.metaprofile.api.metaprofile.models.MetaProfileData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaProfileDataRepository extends JpaRepository<MetaProfileData, Long> {
}
