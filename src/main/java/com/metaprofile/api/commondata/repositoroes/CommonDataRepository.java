package com.metaprofile.api.commondata.repositoroes;

import com.metaprofile.api.commondata.models.CommonData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonDataRepository extends JpaRepository<CommonData, Long> {
    @Query(
            nativeQuery = true,
            value = "SELECT * from common_base " +
                    "WHERE title like '%'||:query||'%' AND mpf_id = :mpfId " +
                    "ORDER BY" +
                    "  CASE" +
                    "    WHEN mpf_id = :mpfId AND title LIKE :query THEN 1" +
                    "    WHEN mpf_id = :mpfId AND title LIKE :query||'%' THEN 2" +
                    "    WHEN mpf_id = :mpfId AND title LIKE '%'||:query THEN 4" +
                    "    WHEN mpf_id = :mpfId THEN 3" +
                    "    ELSE 10" +
                    "  END," +
                    "title LIMIT 20 "
    )
    List<CommonData> findByQuery(@Param("query") String query, @Param("mpfId") Long mpfId);

    /**
     * Возвращает true, если запись найдена
     * @param title
     * @return
     */
    Boolean existsByTitle(String title);
}
