package com.school.app.infrastructure.repositories;
import com.school.app.infrastructure.entities.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaSchoolRepository extends JpaRepository<SchoolEntity, Long> {
    boolean existsByName(String name);
    boolean existsByUsername(String username);
    Optional<SchoolEntity> findByUsername(String username);
}
