package com.school.app.domain.ports.out;

import com.school.app.domain.models.Course;

import java.util.List;
import java.util.Optional;


public interface CourseModelPort {

    Course save(Course course);

    List<Course> findAll();

    Optional<Course> findById(Long id);

    Optional<Course> findByName(String name);

    Course update(Course course);

    Boolean delete(Long id);

    Course logicalDeletion(Course course);
}
