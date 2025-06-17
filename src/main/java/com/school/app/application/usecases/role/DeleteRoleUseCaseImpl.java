package com.school.app.application.usecases.role;

import com.school.app.domain.models.Role;
import com.school.app.domain.ports.in.role.DeleteRoleUseCase;
import com.school.app.domain.ports.out.RoleModelPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteRoleUseCaseImpl implements DeleteRoleUseCase {

    private final RoleModelPort roleModelPort;

    public DeleteRoleUseCaseImpl(RoleModelPort roleModelPort) {
        this.roleModelPort = roleModelPort;
    }

    @Override
    public Boolean delete(Long id) {
        return roleModelPort.delete(id);
    }

    @Override
    public Role logicalDeletion(Role role) {
        return roleModelPort.logicalDeletion(role);
    }
}
