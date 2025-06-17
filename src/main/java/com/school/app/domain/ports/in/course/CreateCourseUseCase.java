package com.school.app.domain.ports.in.course;

import com.school.app.domain.models.Course;

public interface CreateCourseUseCase {
    Course create(Course course);
}
