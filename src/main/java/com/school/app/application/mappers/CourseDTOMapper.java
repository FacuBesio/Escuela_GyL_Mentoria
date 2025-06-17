package com.school.app.application.mappers;

import com.school.app.application.dto.course.CourseDTORequest;
import com.school.app.application.dto.course.CourseDTOResponse;
import com.school.app.application.services.school.impl.SchoolModelService;
import com.school.app.domain.models.School;
import com.school.app.domain.models.Course;
import com.school.app.infrastructure.exceptions.GenericErrorException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CourseDTOMapper {

    @Autowired
    SchoolModelService schoolModelService;

    @Mapping(source = "school_id", target = "school", qualifiedByName = "mapSchoolIdToSchool")
    public abstract Course toModel(CourseDTORequest courseDto);

    @Mapping(source = "school.id", target = "school_id")
    @Mapping(source = "school.name", target = "school")
    public abstract CourseDTOResponse toDto(Course course);

    public abstract List<CourseDTOResponse> toDtoList(List<Course> courses);


    @Named("mapSchoolIdToSchool")
    protected School mapSchoolIdToSchool(Long schoolId) {
        return schoolModelService.getById(schoolId)
                .orElseThrow(() -> new GenericErrorException("Can't create or update Course. School '" + schoolId + "' not found."));
    }

}
