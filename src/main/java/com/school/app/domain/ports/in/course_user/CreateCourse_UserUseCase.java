package com.school.app.domain.ports.in.course_user;

import com.school.app.domain.models.Course_User;

public interface CreateCourse_UserUseCase {

    Course_User create(Course_User course_user);
}
