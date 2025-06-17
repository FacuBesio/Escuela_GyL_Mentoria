package com.school.app.application.mappers;

import com.school.app.application.dto.role.RoleDTORequest;
import com.school.app.application.dto.role.RoleDTOResponse;
import com.school.app.domain.models.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class RoleDTOMapper {

    public abstract Role toModel(RoleDTORequest roleDto);

    public abstract RoleDTOResponse toDto(Role role);

    public abstract List<RoleDTOResponse> toDtoList(List<Role> roles);

}
