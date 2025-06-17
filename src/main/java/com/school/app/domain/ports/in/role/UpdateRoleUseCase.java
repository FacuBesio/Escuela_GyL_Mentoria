package com.school.app.domain.ports.in.role;

import com.school.app.domain.models.Role;

public interface UpdateRoleUseCase {

    Role update(Role role);
}
