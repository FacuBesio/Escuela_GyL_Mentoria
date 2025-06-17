package com.school.app.infrastructure.mappers;

import com.school.app.domain.models.Course;
import com.school.app.infrastructure.entities.SchoolEntity;
import com.school.app.infrastructure.entities.CourseEntity;
import com.school.app.infrastructure.exceptions.GenericErrorException;
import com.school.app.infrastructure.repositories.JpaSchoolRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CourseDomainMapper {

    @Autowired
    public JpaSchoolRepository jpaSchoolRepository;

    @Mapping(source = "school.id", target = "school", qualifiedByName = "mapSchoolIdToSchool")
    public abstract CourseEntity fromDomainModel(Course course);


    @Mapping(source = "school", target = "school")
    public abstract Course toDomainModel(CourseEntity courseEntity);

    public abstract List<CourseEntity> fromDomainModelList(List<Course> courses);

    @Named("mapSchoolIdToSchool")
    protected SchoolEntity mapSchoolIdToSchool(Long schoolId) {
        return jpaSchoolRepository.findById(schoolId)
                .orElseThrow(() -> new GenericErrorException("Can't create or update Course. School '" + schoolId + "' not found."));
    }

}
