package com.school.app.application.usecases.role;

import com.school.app.domain.models.Role;
import com.school.app.domain.ports.in.role.UpdateRoleUseCase;
import com.school.app.domain.ports.out.RoleModelPort;
import org.springframework.stereotype.Service;

@Service
public class UpdateRoleUseCaseImpl implements UpdateRoleUseCase {

    private final RoleModelPort roleModelPort;

    public UpdateRoleUseCaseImpl(RoleModelPort roleModelPort) {
        this.roleModelPort = roleModelPort;
    }

    @Override
    public Role update(Role role) {
        return roleModelPort.update(role);
    }
}
