package com.school.app.application.services.course_user;

import com.school.app.application.dto.course_user.Course_UserDTORequest;
import com.school.app.application.dto.course_user.Course_UserDTOResponse;

import java.util.List;

public interface Course_UserService {

    Course_UserDTOResponse create(Course_UserDTORequest course_userDto);

    List<Course_UserDTOResponse> getAll();

    Course_UserDTOResponse getById(Long id);

    Course_UserDTOResponse update(Long course_userId, Course_UserDTORequest course_userDtoToUpdate);

    Course_UserDTOResponse delete(Long id);

    Course_UserDTOResponse logicalDeletion(Long id);
}
