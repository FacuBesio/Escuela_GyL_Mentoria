package com.school.app.application.usecases.course;

import com.school.app.domain.models.Course;
import com.school.app.domain.ports.in.course.FindCourseUseCase;
import com.school.app.domain.ports.out.CourseModelPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FindCourseUseCaseImpl implements FindCourseUseCase {

    private final CourseModelPort courseModelPort;

    public FindCourseUseCaseImpl(CourseModelPort courseModelPort) {
        this.courseModelPort = courseModelPort;
    }

    @Override
    public List<Course> getAll() {
        return courseModelPort.findAll();
    }

    @Override
    public Optional<Course> getById(Long id) {
        return courseModelPort.findById(id);
    }

    @Override
    public Optional<Course> getByName(String name)  {
        return courseModelPort.findByName(name);
    }

}
