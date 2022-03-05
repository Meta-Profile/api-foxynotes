package com.metaprofile.api.metaprofile.repositories;

import com.metaprofile.api.metaprofile.models.MetaProfileType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaProfileTypesRepository extends JpaRepository<MetaProfileType, Long> {
}
