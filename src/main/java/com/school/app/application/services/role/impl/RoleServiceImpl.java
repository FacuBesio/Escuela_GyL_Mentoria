package com.school.app.application.services.role.impl;

import com.school.app.application.dto.role.RoleDTORequest;
import com.school.app.application.dto.role.RoleDTOResponse;
import com.school.app.application.mappers.RoleDTOMapper;
import com.school.app.application.services.role.RoleService;
import com.school.app.domain.models.Role;
import com.school.app.infrastructure.exceptions.GenericNoContentException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleModelService roleModelService;
    private final RoleDTOMapper roleDTOMapper;

    public RoleServiceImpl(RoleModelService roleModelService, RoleDTOMapper roleDTOMapper) {
        this.roleModelService = roleModelService;
        this.roleDTOMapper = roleDTOMapper;
    }

    @Override
    public RoleDTOResponse create(RoleDTORequest roleDto) {
        roleDto.setEnabled(true);
        Role role = roleDTOMapper.toModel(roleDto);
        Role newRole = roleModelService.create(role);
        return roleDTOMapper.toDto(newRole);
    }

    @Override
    public List<RoleDTOResponse> getAll() {
        List<Role> roles = roleModelService.getAll();
        if (roles.isEmpty()) {
            throw new GenericNoContentException("No roles were found in the database.");
        }
        return roleDTOMapper.toDtoList(roles);
    }

    @Override
    public RoleDTOResponse getById(Long id) {
        Role role = roleModelService.getById(id)
                .orElseThrow(() -> new GenericNoContentException("Role with id '" + id + "' not found."));
        return roleDTOMapper.toDto(role);
    }

    @Override
    public RoleDTOResponse getByName(String name) {
        Role role = roleModelService.getByName(name).
                orElseThrow(() -> new GenericNoContentException("Role with name '" + name + "' not found."));
        return roleDTOMapper.toDto(role);
    }

    @Override
    public RoleDTOResponse update(Long roleId, RoleDTORequest roleDto) {
        Role role = roleModelService.getById(roleId)
                .orElseThrow(() -> new GenericNoContentException("CANNOT UPDATE. Role with id '" + roleId + "' not found."));
        Role roleToUpdate = roleDTOMapper.toModel(roleDto);
        role.setName(roleToUpdate.getName());
        role.setEnabled(roleToUpdate.getEnabled());
        Role updatedRole = roleModelService.update(role);
        return roleDTOMapper.toDto(updatedRole);
    }

    @Override
    public RoleDTOResponse delete(Long id) {
        Role role = roleModelService.getById(id)
                .orElseThrow(() -> new GenericNoContentException("CANNOT DELETE. Role with id '" + id + "' not found."));
        Boolean isDelete = roleModelService.delete(id);
        if (isDelete == true) {
            return roleDTOMapper.toDto(role);
        }
        throw new GenericNoContentException("CANNOT DELETE. Something went wrong during the process. Try again.");
    }

    @Override
    public RoleDTOResponse logicalDeletion(Long id) {
        Role role = roleModelService.getById(id)
                .orElseThrow(() -> new GenericNoContentException("CANNOT DISABLE. Role with id '" + id + "' not found."));
        role.setEnabled(false);
        Role disableRole = roleModelService.logicalDeletion(role);
        return roleDTOMapper.toDto(disableRole);
    }
}
