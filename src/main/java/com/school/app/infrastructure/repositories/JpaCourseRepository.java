package com.school.app.infrastructure.repositories;

import com.school.app.infrastructure.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaCourseRepository extends JpaRepository<CourseEntity, Long> {
    boolean existsByName(String name);

    Optional<CourseEntity> findByName(String name);

    @Query("SELECT c FROM CourseEntity c WHERE c.school.id = :schoolId")
    List<CourseEntity> findCoursesBySchoolId(@Param("schoolId") Long schoolId);
}
