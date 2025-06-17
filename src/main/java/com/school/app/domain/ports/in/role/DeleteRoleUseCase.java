package com.school.app.domain.ports.in.role;

import com.school.app.domain.models.Role;

public interface DeleteRoleUseCase {

   Boolean delete(Long id);
   Role logicalDeletion(Role role);
}
