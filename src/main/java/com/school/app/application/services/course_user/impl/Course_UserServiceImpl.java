package com.school.app.application.services.course_user.impl;

import com.school.app.application.dto.course_user.Course_UserDTORequest;
import com.school.app.application.dto.course_user.Course_UserDTOResponse;
import com.school.app.application.mappers.Course_UserDTOMapper;
import com.school.app.application.services.course_user.Course_UserService;
import com.school.app.domain.models.Course_User;
import com.school.app.infrastructure.exceptions.GenericNoContentException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Course_UserServiceImpl implements Course_UserService {

    private final Course_UserModelService course_userModelService;
    private final Course_UserDTOMapper course_userDTOMapper;

    public Course_UserServiceImpl(Course_UserModelService course_userModelService, Course_UserDTOMapper course_userDTOMapper) {
        this.course_userModelService = course_userModelService;
        this.course_userDTOMapper = course_userDTOMapper;
    }

    @Override
    public Course_UserDTOResponse create(Course_UserDTORequest course_userDto) {
        course_userDto.setEnabled(true);
        Course_User course_user = course_userDTOMapper.toModel(course_userDto);
        Course_User newCourse_User = course_userModelService.create(course_user);
        return course_userDTOMapper.toDto(newCourse_User);
    }

    @Override
    public List<Course_UserDTOResponse> getAll() {
        List<Course_User> course_users = course_userModelService.getAll();
        if (course_users.isEmpty()) {
            throw new GenericNoContentException("No course_users were found in the database.");
        }
        return course_userDTOMapper.toDtoList(course_users);
    }

    @Override
    public Course_UserDTOResponse getById(Long id) {
        Course_User course_user = course_userModelService.getById(id)
                .orElseThrow(() -> new GenericNoContentException("Course_User with id '" + id + "' not found."));
        return course_userDTOMapper.toDto(course_user);
    }

    @Override
    public Course_UserDTOResponse update(Long course_userId, Course_UserDTORequest course_userDto) {
        Course_User course_user = course_userModelService.getById(course_userId)
                .orElseThrow(() -> new GenericNoContentException("CANNOT UPDATE. Course_User with id '" + course_userId + "' not found."));
        Course_User course_userToUpdate = course_userDTOMapper.toModel(course_userDto);
        course_user.setCourse(course_userToUpdate.getCourse());
        course_user.setUser(course_userToUpdate.getUser());
        course_user.setEnabled(course_userToUpdate.getEnabled());
        Course_User updatedCourse_User = course_userModelService.update(course_user);
        return course_userDTOMapper.toDto(updatedCourse_User);
    }

    @Override
    public Course_UserDTOResponse delete(Long id) {
        Course_User course_user = course_userModelService.getById(id)
                .orElseThrow(() -> new GenericNoContentException("CANNOT DELETE. Course_User with id '" + id + "' not found."));
        Boolean isDelete = course_userModelService.delete(id);
        if (isDelete == true) {
            return course_userDTOMapper.toDto(course_user);
        }
        throw new GenericNoContentException("CANNOT DELETE. Something went wrong during the process. Try again.");
    }

    @Override
    public Course_UserDTOResponse logicalDeletion(Long id) {
        Course_User course_user = course_userModelService.getById(id)
                .orElseThrow(() -> new GenericNoContentException("CANNOT DISABLE. Course_User with id '" + id + "' not found."));
        course_user.setEnabled(false);
        Course_User disableCourse_User = course_userModelService.logicalDeletion(course_user);
        return course_userDTOMapper.toDto(disableCourse_User);
    }
}
