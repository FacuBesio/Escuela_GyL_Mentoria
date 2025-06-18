package com.school.app.domain.ports.out;


import com.school.app.domain.models.Course_User;
import com.school.app.domain.models.User;

import java.util.List;
import java.util.Optional;


public interface Course_UserModelPort {

    Course_User save(Course_User course_user);

    List<Course_User> findAll();

    Optional<Course_User> findById(Long id);

    Course_User update(Course_User course_user);

    Boolean delete(Long id);

    Course_User logicalDeletion(Course_User course_user);

    List<User> getUsersByCourseId(Long courseId);
}
