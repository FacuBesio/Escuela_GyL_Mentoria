package com.school.app.infrastructure.mappers;

import com.school.app.domain.models.School;
import com.school.app.infrastructure.entities.SchoolEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SchoolDomainMapper {

    public abstract SchoolEntity fromDomainModel(School school);

    public abstract School toDomainModel(SchoolEntity schoolEntity);

    public abstract List<SchoolEntity> fromDomainModelList(List<School> schools);
}
