package com.school.app.application.usecases.course_user;

import com.school.app.domain.models.Course_User;
import com.school.app.domain.ports.in.course_user.UpdateCourse_UserUseCase;
import com.school.app.domain.ports.out.Course_UserModelPort;
import org.springframework.stereotype.Service;

@Service
public class UpdateCourse_UserUseCaseImpl implements UpdateCourse_UserUseCase {

    private final Course_UserModelPort course_userModelPort;

    public UpdateCourse_UserUseCaseImpl(Course_UserModelPort course_userModelPort) {
        this.course_userModelPort = course_userModelPort;
    }

    @Override
    public Course_User update(Course_User course_user) {
        return course_userModelPort.update(course_user);
    }
}
