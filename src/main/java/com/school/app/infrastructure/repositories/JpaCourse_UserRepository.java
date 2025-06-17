package com.school.app.infrastructure.repositories;

import com.school.app.infrastructure.entities.CourseEntity;
import com.school.app.infrastructure.entities.Course_UserEntity;
import com.school.app.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JpaCourse_UserRepository extends JpaRepository<Course_UserEntity, Long> {
    @Query("SELECT su.user FROM Course_UserEntity su WHERE su.course.id = :courseId")
    List<UserEntity> findUsersByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT su.course FROM Course_UserEntity su WHERE su.user.id = :userId")
    List<CourseEntity> findCoursesByUserId(@Param("userId") Long userId);

    @Query("SELECT COUNT(su) > 0 FROM Course_UserEntity su WHERE su.user.id = :userId AND su.course.id = :courseId")
    boolean existsByUserIdAndCourseId(@Param("userId") Long userId, @Param("courseId") Long courseId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Course_UserEntity su WHERE su.user.id = :userId AND su.course.id = :courseId")
    int deleteByUserIdAndCourseId(@Param("userId") Long userId, @Param("courseId") Long courseId);

    @Query("SELECT COUNT(su) > 0 FROM Course_UserEntity su WHERE su.user.id = :userId")
    boolean existsByUserId(@Param("userId") Long userId);

}
