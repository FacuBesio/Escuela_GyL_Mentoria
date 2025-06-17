package com.school.app.infrastructure.mappers;

import com.school.app.domain.models.Role;
import com.school.app.infrastructure.entities.RoleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class RoleDomainMapper {

    public abstract RoleEntity fromDomainModel(Role role);

    public abstract Role toDomainModel(RoleEntity roleEntity);

    public abstract List<RoleEntity> fromDomainModelList(List<Role> roles);
}
