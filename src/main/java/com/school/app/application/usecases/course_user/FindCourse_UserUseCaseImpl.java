package com.school.app.application.usecases.course_user;

import com.school.app.domain.models.Course_User;
import com.school.app.domain.ports.in.course_user.FindCourse_UserUseCase;
import com.school.app.domain.ports.out.Course_UserModelPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FindCourse_UserUseCaseImpl implements FindCourse_UserUseCase {

    private final Course_UserModelPort course_userModelPort;

    public FindCourse_UserUseCaseImpl(Course_UserModelPort course_userModelPort) {
        this.course_userModelPort = course_userModelPort;
    }

    @Override
    public Optional<Course_User> getById(Long id) {
        return course_userModelPort.findById(id);
    }

    @Override
    public List<Course_User> getAll() {
        return course_userModelPort.findAll();
    }
}
