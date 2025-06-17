package com.school.app.domain.ports.in.course_user;

import com.school.app.domain.models.Course_User;

import java.util.List;
import java.util.Optional;

public interface FindCourse_UserUseCase {

    Optional<Course_User> getById(Long id);

    List<Course_User> getAll();
}
