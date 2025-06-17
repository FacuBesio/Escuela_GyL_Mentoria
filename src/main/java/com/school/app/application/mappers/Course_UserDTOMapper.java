package com.school.app.application.mappers;

import com.school.app.application.dto.course_user.Course_UserDTORequest;
import com.school.app.application.dto.course_user.Course_UserDTOResponse;
import com.school.app.application.services.course.impl.CourseModelService;
import com.school.app.application.services.user.impl.UserModelService;
import com.school.app.domain.models.Course;
import com.school.app.domain.models.Course_User;
import com.school.app.domain.models.User;
import com.school.app.infrastructure.entities.CourseEntity;
import com.school.app.infrastructure.entities.UserEntity;
import com.school.app.infrastructure.exceptions.GenericErrorException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class Course_UserDTOMapper {

    @Autowired
    UserModelService userModelService;
    @Autowired
    CourseModelService courseModelService;

    @Mapping(source = "course_id", target = "course", qualifiedByName = "mapCourseIdToCourse")
    @Mapping(source = "user_id", target = "user", qualifiedByName = "mapUserIdToUser")
    public abstract Course_User toModel(Course_UserDTORequest course_userDto);


    @Mapping(source = "course.id", target = "course_id")
    @Mapping(source = "user.id", target = "user_id")
    public abstract Course_UserDTOResponse toDto(Course_User course_user);

    public abstract List<Course_UserDTOResponse> toDtoList(List<Course_User> course_users);

    @Named("mapCourseIdToCourse")
    protected Course mapCourseIdToCourse(Long courseId) {
        return courseModelService.getById(courseId)
                .orElseThrow(() -> new GenericErrorException("Can't create or update Course_User relation. Course '" + courseId + "' not found."));
    }

    @Named("mapUserIdToUser")
    protected User mapUserIdToUser(Long userId) {
        return userModelService.getById(userId)
                .orElseThrow(() -> new GenericErrorException("Can't create or update Course_User relation. User '" + userId + "' not found."));
    }

}
