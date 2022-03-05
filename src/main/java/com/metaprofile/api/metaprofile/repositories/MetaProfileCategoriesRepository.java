package com.metaprofile.api.metaprofile.repositories;

import com.metaprofile.api.metaprofile.models.MetaProfileCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MetaProfileCategoriesRepository extends JpaRepository<MetaProfileCategory, Long> {
    @Query(
            nativeQuery = true,
            value = "SELECT * from meta_profile_categories " +
                    "WHERE upper(title ->> :lang) like upper('%'||:query||'%') " +
                    "ORDER BY" +
                    "  CASE" +
                    "    WHEN upper(title ->> :lang) LIKE upper(:query) THEN 1" +
                    "    WHEN upper(title ->> :lang) LIKE upper(:query||'%') THEN 2" +
                    "    WHEN upper(title ->> :lang) LIKE upper('%'||:query) THEN 4" +
                    "    ELSE 3" +
                    "  END," +
                    "title ->> :lang ASC LIMIT 20 "
    )
    List<MetaProfileCategory> findByQuery(@Param("query") String query, @Param("lang") String lang);

}
