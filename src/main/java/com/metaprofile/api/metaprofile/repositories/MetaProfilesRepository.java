package com.metaprofile.api.metaprofile.repositories;

import com.metaprofile.api.metaprofile.models.MetaProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaProfilesRepository extends JpaRepository<MetaProfile, Long> {
}
