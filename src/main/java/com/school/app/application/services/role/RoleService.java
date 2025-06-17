package com.school.app.application.services.role;

import com.school.app.application.dto.role.RoleDTORequest;
import com.school.app.application.dto.role.RoleDTOResponse;

import java.util.List;

public interface RoleService {

    RoleDTOResponse create(RoleDTORequest roleDto);

    List<RoleDTOResponse> getAll();

    RoleDTOResponse getById(Long id);

    RoleDTOResponse getByName(String name);

    RoleDTOResponse update(Long roleId, RoleDTORequest roleDtoToUpdate);

    RoleDTOResponse delete(Long id);

    RoleDTOResponse logicalDeletion(Long id);
}
