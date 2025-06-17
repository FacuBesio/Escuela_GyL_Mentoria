package com.school.app.infrastructure.repositories;

import com.school.app.infrastructure.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    Page<UserEntity> findAll(Pageable pageable);

    Optional<UserEntity> findByUsername(String username);

    @Query("SELECT u FROM UserEntity u " +
            "WHERE (:username IS NULL OR LOWER(u.username) LIKE LOWER(CONCAT('%', :username, '%'))) " +
            "AND (:role IS NULL OR :role = '' OR u.role.name = :role) " +
            "AND (:enabled IS NULL OR u.enabled = :enabled)")
    Page<UserEntity> filterUsersByParams(Pageable pageable,
                                         @Param("username") String username,
                                         @Param("role") String role,
                                         @Param("enabled") Boolean enabled);

    @Query("SELECT u FROM UserEntity u WHERE u.school.id = :schoolId")
    List<UserEntity> findBySchoolId(@Param("schoolId") Long schoolId);

//    @Query("SELECT u FROM UserEntity u " +
//            "LEFT JOIN u.squad_users su " +
//            "WHERE u.school.id = :schoolId " +
//            "AND su.id IS NULL")
//    List<UserEntity> findBySchoolIdAndHasNoSquad(@Param("schoolId") Long schoolId);

    boolean existsByUsername(String user);
}
