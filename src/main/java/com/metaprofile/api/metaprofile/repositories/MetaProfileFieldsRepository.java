package com.metaprofile.api.metaprofile.repositories;

import com.metaprofile.api.metaprofile.models.MetaProfileField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MetaProfileFieldsRepository extends JpaRepository<MetaProfileField, Long> {
    @Query(
            nativeQuery = true,
            value = "SELECT * from meta_profile_fields " +
                    "WHERE mpc_id = :mpcId " +
                    "and upper(title ->> :lang) like upper('%'||:query||'%') " +
                    "ORDER BY" +
                    "  CASE" +
                    "    WHEN upper(title ->> :lang) LIKE upper(:query) THEN 1" +
                    "    WHEN upper(title ->> :lang) LIKE upper(:query||'%') THEN 2" +
                    "    WHEN upper(title ->> :lang) LIKE upper('%'||:query) THEN 4" +
                    "    ELSE 3" +
                    "  END," +
                    "title ->> :lang LIMIT 20 "
    )
    List<MetaProfileField> findByQuery(@Param("query") String query, @Param("mpcId") Long mpcId, @Param("lang") String lang);

    List<MetaProfileField> findAllByMpcId(Long mpcId);
}
