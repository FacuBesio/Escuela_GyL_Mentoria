package com.school.app.application.usecases.role;

import com.school.app.domain.models.Role;
import com.school.app.domain.ports.in.role.CreateRoleUseCase;
import com.school.app.domain.ports.out.RoleModelPort;
import org.springframework.stereotype.Service;

@Service
public class CreateRoleUseCaseImpl implements CreateRoleUseCase {

    private final RoleModelPort roleModelPort;

    public CreateRoleUseCaseImpl(RoleModelPort roleModelPort) {
        this.roleModelPort = roleModelPort;
    }

    @Override
    public Role create(Role role) {
        return roleModelPort.save(role);
    }
}
