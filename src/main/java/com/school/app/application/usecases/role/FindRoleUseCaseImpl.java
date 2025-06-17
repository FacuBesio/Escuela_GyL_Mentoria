package com.school.app.application.usecases.role;

import com.school.app.domain.models.Role;
import com.school.app.domain.ports.in.role.FindRoleUseCase;
import com.school.app.domain.ports.out.RoleModelPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FindRoleUseCaseImpl implements FindRoleUseCase {

    private final RoleModelPort roleModelPort;

    public FindRoleUseCaseImpl(RoleModelPort roleModelPort) {
        this.roleModelPort = roleModelPort;
    }

    @Override
    public Optional<Role> getById(Long id) {
        return roleModelPort.findById(id);
    }

    @Override
    public Optional<Role> getByName(String name) {
        return roleModelPort.findByName(name);
    }

    @Override
    public List<Role> getAll() {
        return roleModelPort.findAll();
    }
}
