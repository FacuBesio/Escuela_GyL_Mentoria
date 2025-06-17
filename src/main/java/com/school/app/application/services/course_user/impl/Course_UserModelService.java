package com.school.app.application.services.course_user.impl;

import com.school.app.domain.models.Course_User;
import com.school.app.domain.ports.in.course_user.CreateCourse_UserUseCase;
import com.school.app.domain.ports.in.course_user.DeleteCourse_UserUseCase;
import com.school.app.domain.ports.in.course_user.FindCourse_UserUseCase;
import com.school.app.domain.ports.in.course_user.UpdateCourse_UserUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Course_UserModelService implements CreateCourse_UserUseCase, DeleteCourse_UserUseCase, UpdateCourse_UserUseCase, FindCourse_UserUseCase {

    private final CreateCourse_UserUseCase createCourse_UserUseCase;
    private final DeleteCourse_UserUseCase deleteCourse_UserUseCase;
    private final UpdateCourse_UserUseCase updateCourse_UserUseCase;
    private final FindCourse_UserUseCase findCourse_UserUseCase;

    public Course_UserModelService(CreateCourse_UserUseCase createCourse_UserUseCase, DeleteCourse_UserUseCase deleteCourse_UserUseCase, UpdateCourse_UserUseCase updateCourse_UserUseCase, FindCourse_UserUseCase findCourse_UserUseCase) {
        this.createCourse_UserUseCase = createCourse_UserUseCase;
        this.deleteCourse_UserUseCase = deleteCourse_UserUseCase;
        this.updateCourse_UserUseCase = updateCourse_UserUseCase;
        this.findCourse_UserUseCase = findCourse_UserUseCase;
    }

    @Override
    public Course_User create(Course_User course_user) {
        return createCourse_UserUseCase.create(course_user);
    }

    @Override
    public Boolean delete(Long id) {
        return deleteCourse_UserUseCase.delete(id);
    }

    @Override
    public Course_User logicalDeletion(Course_User course_user) {
        return deleteCourse_UserUseCase.logicalDeletion(course_user);
    }

    @Override
    public Optional<Course_User> getById(Long id) {
        return findCourse_UserUseCase.getById(id);
    }

    @Override
    public List<Course_User> getAll() {
        return findCourse_UserUseCase.getAll();
    }

    @Override
    public Course_User update(Course_User course_user) {
        return updateCourse_UserUseCase.update(course_user);
    }
}
