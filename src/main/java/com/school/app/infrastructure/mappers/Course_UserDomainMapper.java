package com.school.app.infrastructure.mappers;

import com.school.app.domain.models.Course_User;
import com.school.app.infrastructure.entities.CourseEntity;
import com.school.app.infrastructure.entities.Course_UserEntity;
import com.school.app.infrastructure.entities.UserEntity;
import com.school.app.infrastructure.exceptions.GenericErrorException;
import com.school.app.infrastructure.repositories.JpaCourseRepository;
import com.school.app.infrastructure.repositories.JpaUserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class Course_UserDomainMapper {

    @Autowired
    JpaUserRepository jpaUserRepository;
    @Autowired
    JpaCourseRepository jpaCourseRepository;

     @Mapping(source = "course.id", target = "course", qualifiedByName = "mapCourseIdToCourse")
    @Mapping(source = "user.id", target = "user", qualifiedByName = "mapUserIdToUser")
    public abstract Course_UserEntity fromDomainModel(Course_User course_user);


    @Mapping(source = "course", target = "course")
    @Mapping(source = "user", target = "user")
    public abstract Course_User toDomainModel(Course_UserEntity course_userEntity);

    public abstract List<Course_UserEntity> fromDomainModelList(List<Course_User> course_users);

    @Named("mapCourseIdToCourse")
    protected CourseEntity mapCourseIdToCourse(Long courseId) {
        return jpaCourseRepository.findById(courseId)
                .orElseThrow(() -> new GenericErrorException("Can't create or update Course-User relation. Course '"+courseId+"' not found."));
    }

    @Named("mapUserIdToUser")
    protected UserEntity mapUserIdToUser(Long userId) {
        return jpaUserRepository.findById(userId)
                .orElseThrow(() -> new GenericErrorException("Can't create or update Course-User relation. User '"+userId+"' not found."));
    }

}
