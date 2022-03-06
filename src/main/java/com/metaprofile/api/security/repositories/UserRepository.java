package com.metaprofile.api.security.repositories;

import com.metaprofile.api.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Поиск пользователя по логин
     * @param username - логин
     * @return - пользователь
     */
    Optional<User> findByUsername(String username);

    /**
     * Выполняет поиск пользователя по паролю и (metaId или email)
     * @param emailOrMetaId
     * @param password
     * @return
     */
    @Query(
        nativeQuery = true,
        value = "SELECT * FROM users WHERE email = :emailOrMetaId OR username = :emailOrMetaId"
    )
    Optional<User> findByEmailOrMetaId(@Param("emailOrMetaId") String emailOrMetaId);

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}
