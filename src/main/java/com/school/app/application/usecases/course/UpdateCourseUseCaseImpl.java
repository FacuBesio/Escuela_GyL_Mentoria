package com.school.app.application.usecases.course;

import com.school.app.domain.models.Course;
import com.school.app.domain.ports.in.course.UpdateCourseUseCase;
import com.school.app.domain.ports.out.CourseModelPort;
import org.springframework.stereotype.Service;

@Service
public class UpdateCourseUseCaseImpl implements UpdateCourseUseCase {

    private final CourseModelPort courseModelPort;

    public UpdateCourseUseCaseImpl(CourseModelPort courseModelPort) {
        this.courseModelPort = courseModelPort;
    }

    @Override
    public Course update(Course course) {
        return courseModelPort.update(course);
    }
}
