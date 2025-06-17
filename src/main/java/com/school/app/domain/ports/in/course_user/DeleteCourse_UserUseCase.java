package com.school.app.domain.ports.in.course_user;

import com.school.app.domain.models.Course_User;

public interface DeleteCourse_UserUseCase {

   Boolean delete(Long id);
   Course_User logicalDeletion(Course_User course_user);
}
