package com.school.app.domain.ports.in.role;

import com.school.app.domain.models.Role;

public interface CreateRoleUseCase {

    Role create(Role role);
}
