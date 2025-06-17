package com.school.app.application.services.course.impl;

import com.school.app.domain.models.Course;
import com.school.app.domain.ports.in.course.CreateCourseUseCase;
import com.school.app.domain.ports.in.course.DeleteCourseUseCase;
import com.school.app.domain.ports.in.course.FindCourseUseCase;
import com.school.app.domain.ports.in.course.UpdateCourseUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseModelService implements CreateCourseUseCase, DeleteCourseUseCase, UpdateCourseUseCase, FindCourseUseCase {

    private final CreateCourseUseCase createCourseUseCase;
    private final DeleteCourseUseCase deleteCourseUseCase;
    private final UpdateCourseUseCase updateCourseUseCase;
    private final FindCourseUseCase findCourseUseCase;

    public CourseModelService(CreateCourseUseCase createCourseUseCase, DeleteCourseUseCase deleteCourseUseCase, UpdateCourseUseCase updateCourseUseCase, FindCourseUseCase findCourseUseCase) {
        this.createCourseUseCase = createCourseUseCase;
        this.deleteCourseUseCase = deleteCourseUseCase;
        this.updateCourseUseCase = updateCourseUseCase;
        this.findCourseUseCase = findCourseUseCase;
    }

    @Override
    public Course create(Course course) {
        return createCourseUseCase.create(course);
    }

    @Override
    public Boolean delete(Long id) {
        return deleteCourseUseCase.delete(id);
    }

    @Override
    public Course logicalDeletion(Course course) {
        return deleteCourseUseCase.logicalDeletion(course);
    }

    @Override
    public Optional<Course> getById(Long id) {
        return findCourseUseCase.getById(id);
    }

    @Override
    public Optional<Course> getByName(String name) {
        return findCourseUseCase.getByName(name);
    }

    @Override
    public List<Course> getAll() {
        return findCourseUseCase.getAll();
    }

    @Override
    public Course update(Course course) {
        return updateCourseUseCase.update(course);
    }
}
