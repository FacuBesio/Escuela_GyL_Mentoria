package com.school.app.domain.ports.in.course_user;

import com.school.app.domain.models.Course_User;

public interface UpdateCourse_UserUseCase {

    Course_User update(Course_User course_user);
}
