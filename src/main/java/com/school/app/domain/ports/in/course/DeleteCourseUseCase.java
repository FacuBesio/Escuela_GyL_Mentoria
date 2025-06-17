package com.school.app.domain.ports.in.course;

import com.school.app.domain.models.Course;

public interface DeleteCourseUseCase {
    Boolean delete(Long id);

    Course logicalDeletion(Course course);
}
