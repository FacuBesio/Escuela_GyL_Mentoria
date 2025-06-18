package com.school.app.infrastructure.repositories;

import com.school.app.infrastructure.entities.Course_UserEntity;
import com.school.app.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaCourse_UserRepository extends JpaRepository<Course_UserEntity, Long> {
    @Query("SELECT cu.user FROM Course_UserEntity cu WHERE cu.course.id = :courseId")
    List<UserEntity> findUsersByCourseId(@Param("courseId") Long courseId);

}
