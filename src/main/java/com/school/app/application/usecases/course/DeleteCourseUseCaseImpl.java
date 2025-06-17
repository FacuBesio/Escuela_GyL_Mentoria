package com.school.app.application.usecases.course;

import com.school.app.domain.models.Course;
import com.school.app.domain.ports.in.course.DeleteCourseUseCase;
import com.school.app.domain.ports.out.CourseModelPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteCourseUseCaseImpl implements DeleteCourseUseCase {

    private final CourseModelPort courseModelPort;

    public DeleteCourseUseCaseImpl(CourseModelPort courseModelPort) {
        this.courseModelPort = courseModelPort;
    }

    @Override
    public Boolean delete(Long id) {
        return courseModelPort.delete(id);
    }

    @Override
    public Course logicalDeletion(Course course) {
        return courseModelPort.logicalDeletion(course);
    }
}
