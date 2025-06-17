package com.school.app.application.mappers;

import com.school.app.application.dto.school.SchoolDTORequest;
import com.school.app.application.dto.school.SchoolDTOResponse;
import com.school.app.domain.models.School;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SchoolDTOMapper {

    public abstract School toModel(SchoolDTORequest schoolDto);

    public abstract SchoolDTOResponse toDto(School school);

    public abstract List<SchoolDTOResponse> toDtoList(List<School> schools);

}
