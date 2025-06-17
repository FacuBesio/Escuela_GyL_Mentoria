package com.school.app.domain.ports.in.course;

import com.school.app.domain.models.Course;

public interface UpdateCourseUseCase {

    Course update(Course course);
}
