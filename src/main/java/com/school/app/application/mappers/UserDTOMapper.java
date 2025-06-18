package com.school.app.application.mappers;

import com.school.app.application.dto.course.CourseDTOResponse;
import com.school.app.application.dto.user.UserDTORequest;
import com.school.app.application.dto.user.UserDTOResponse;
import com.school.app.application.dto.user.UserDTOResponseSimplified;
import com.school.app.application.services.role.impl.RoleModelService;
import com.school.app.application.services.school.impl.SchoolModelService;
import com.school.app.domain.models.*;
import com.school.app.infrastructure.exceptions.GenericErrorException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UserDTOMapper {

    @Autowired
    SchoolModelService schoolModelService;
    @Autowired
    RoleModelService roleModelService;

    @Mapping(source = "school_id", target = "school", qualifiedByName = "mapSchoolIdToSchool")
    @Mapping(source = "role_id", target = "role", qualifiedByName = "mapRoleIdToRole")
    public abstract User toModel(UserDTORequest userDto);

    @Mapping(source = "school.id", target = "school_id")
    @Mapping(source = "school.name", target = "school")
    @Mapping(source = "role.id", target = "role_id")
    @Mapping(source = "role.name", target = "role")
    @Mapping(source = "courses", target = "courses", qualifiedByName = "mapCoursesToCourseDTOs")
    public abstract UserDTOResponse toDto(User user);

    public abstract List<UserDTOResponse> toDtoList(List<User> users);

    public abstract UserDTOResponseSimplified toSimplifiedDTO(UserDTOResponse userDTOResponse);

    @Named("mapSchoolIdToSchool")
    protected School mapSchoolIdToSchool(Long schoolId) {
        return schoolModelService.getById(schoolId)
                .orElseThrow(() -> new GenericErrorException("Can't create or update User. School '" + schoolId + "' not found."));
    }

    @Named("mapRoleIdToRole")
    protected Role mapRoleIdToRole(Long roleId) {
        return roleModelService.getById(roleId)
                .orElseThrow(() -> new GenericErrorException("Can't create or update User. Role '" + roleId + "' not found."));
    }

    @Named("mapCoursesToCourseDTOs")
    protected List<CourseDTOResponse> mapCoursesToCourseDTOs(List<Course> courses) {
        if (courses == null) {
            return Collections.emptyList();
        }
        return courses.stream()
                .filter(Objects::nonNull)
                .map(course -> {
                    CourseDTOResponse courseDTO = new CourseDTOResponse();
                    courseDTO.setId(course.getId());
                    courseDTO.setSchool_id(course.getSchool().getId());
                    courseDTO.setSchool(course.getSchool().getName());
                    courseDTO.setName(course.getName());
                    courseDTO.setDescription(course.getDescription());
                    courseDTO.setEnabled(course.getEnabled());
                    return courseDTO;
                }).collect(Collectors.toList());
    }
}
