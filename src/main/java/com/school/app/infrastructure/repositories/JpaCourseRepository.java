package com.school.app.infrastructure.repositories;

import com.school.app.infrastructure.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaCourseRepository extends JpaRepository<CourseEntity, Long> {
    boolean existsByName(String name);

    Optional<CourseEntity> findByName(String name);
}
