package com.school.app.application.usecases.course_user;

import com.school.app.domain.models.Course_User;
import com.school.app.domain.ports.in.course_user.DeleteCourse_UserUseCase;
import com.school.app.domain.ports.out.Course_UserModelPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteCourse_UserUseCaseImpl implements DeleteCourse_UserUseCase {

    private final Course_UserModelPort course_userModelPort;

    public DeleteCourse_UserUseCaseImpl(Course_UserModelPort course_userModelPort) {
        this.course_userModelPort = course_userModelPort;
    }

    @Override
    public Boolean delete(Long id) {
        return course_userModelPort.delete(id);
    }

    @Override
    public Course_User logicalDeletion(Course_User course_user) {
        return course_userModelPort.logicalDeletion(course_user);
    }
}
