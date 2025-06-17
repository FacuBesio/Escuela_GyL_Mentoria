package com.school.app.infrastructure.mappers;

import com.school.app.domain.models.Course;
import com.school.app.domain.models.User;
import com.school.app.infrastructure.entities.*;
import com.school.app.infrastructure.exceptions.GenericErrorException;
import com.school.app.infrastructure.repositories.JpaSchoolRepository;
import com.school.app.infrastructure.repositories.JpaRoleRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UserDomainMapper {

    @Autowired
    public JpaSchoolRepository jpaSchoolRepository;
    @Autowired
    public JpaRoleRepository jpaRoleRepository;
    @Autowired
    public SchoolDomainMapper schoolDomainMapper;


    @Mapping(source = "school.id", target = "school", qualifiedByName = "mapSchoolIdToSchool")
    @Mapping(source = "role.id", target = "role", qualifiedByName = "mapRoleIdToRole")
    @Mapping(source = "courses", target = "course_users", qualifiedByName = "mapCoursesToCourseUsers")
    public abstract UserEntity fromDomainModel(User user);


    @Mapping(source = "school", target = "school")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "course_users", target = "courses", qualifiedByName = "mapCourseUsersToCourses")
    public abstract User toDomainModel(UserEntity userEntity);

    public abstract List<UserEntity> fromDomainModelList(List<User> users);

    @Named("mapSchoolIdToSchool")
    protected SchoolEntity mapSchoolIdToSchool(Long schoolId) {
        return jpaSchoolRepository.findById(schoolId)
                .orElseThrow(() -> new GenericErrorException("Can't create or update User. School '" + schoolId + "' not found."));
    }

    @Named("mapRoleIdToRole")
    protected RoleEntity mapRoleIdToRole(Long roleId) {
        return jpaRoleRepository.findById(roleId)
                .orElseThrow(() -> new GenericErrorException("Can't create or update User. Role '" + roleId + "' not found."));
    }

    @Named("mapCoursesToCourseUsers")
    protected List<Course_UserEntity> mapCoursesToCourseUsers(List<Course> courses) {
        if (courses == null) {
            return null;
        }
        return courses.stream()
                .map(course -> {
                    Course_UserEntity courseUserEntity = new Course_UserEntity();
                    courseUserEntity.setCourse(course_fromDomainModelManual(course));
                    return courseUserEntity;
                })
                .collect(Collectors.toList());
    }

    private CourseEntity course_fromDomainModelManual(Course course) {
        if (course == null) {
            return null;
        }
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(course.getId());
        courseEntity.setName(course.getName());
        courseEntity.setDescription(course.getDescription());
        courseEntity.setEnabled(course.getEnabled());
        return courseEntity;
    }

    @Named("mapCourseUsersToCourses")
    protected List<Course> mapCourseUsersToCourses(List<Course_UserEntity> courseUsers) {
        if (courseUsers == null) {
            return null;
        }
        return courseUsers.stream()
                .map(courseUser -> toDomainModelManual(courseUser.getCourse()))
                .collect(Collectors.toList());
    }


    private Course toDomainModelManual(CourseEntity courseEntity) {
        if (courseEntity == null) {
            return null;
        }
        Course course = new Course();
        course.setId(courseEntity.getId());
        course.setSchool(schoolDomainMapper.toDomainModel(courseEntity.getSchool()));
        course.setName(courseEntity.getName());
        course.setDescription(courseEntity.getDescription());
        course.setEnabled(courseEntity.getEnabled());
        return course;
    }

}
