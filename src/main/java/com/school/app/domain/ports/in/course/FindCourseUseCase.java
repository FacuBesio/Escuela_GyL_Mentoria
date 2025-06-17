package com.school.app.domain.ports.in.course;

import com.school.app.domain.models.Course;

import java.util.List;
import java.util.Optional;

public interface FindCourseUseCase {
    List<Course> getAll();

    Optional<Course> getById(Long id);

    Optional<Course> getByName(String name);
}
