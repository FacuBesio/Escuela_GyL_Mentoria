package com.school.app.application.usecases.course;

import com.school.app.domain.models.Course;
import com.school.app.domain.ports.in.course.CreateCourseUseCase;
import com.school.app.domain.ports.out.CourseModelPort;
import org.springframework.stereotype.Service;

@Service
public class CreateCourseUseCaseImpl implements CreateCourseUseCase {

    private final CourseModelPort courseModelPort;

    public CreateCourseUseCaseImpl(CourseModelPort courseModelPort) {
        this.courseModelPort = courseModelPort;
    }

    @Override
    public Course create(Course course) {
        return courseModelPort.save(course);
    }
}
