package com.metaprofile.api.security.repositories;

import com.metaprofile.api.security.models.UserLoginSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginSessionsRepository extends JpaRepository<UserLoginSession, Long> {
    /**
     * Выполняет поиск сессии по токену
     * @param token
     * @return
     */
    Optional<UserLoginSession> findByToken(String token);
}
