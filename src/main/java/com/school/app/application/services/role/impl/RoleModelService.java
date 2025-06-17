package com.school.app.application.services.role.impl;

import com.school.app.domain.models.Role;
import com.school.app.domain.ports.in.role.CreateRoleUseCase;
import com.school.app.domain.ports.in.role.DeleteRoleUseCase;
import com.school.app.domain.ports.in.role.FindRoleUseCase;
import com.school.app.domain.ports.in.role.UpdateRoleUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleModelService implements CreateRoleUseCase, DeleteRoleUseCase, UpdateRoleUseCase, FindRoleUseCase {

    private final CreateRoleUseCase createRoleUseCase;
    private final DeleteRoleUseCase deleteRoleUseCase;
    private final UpdateRoleUseCase updateRoleUseCase;
    private final FindRoleUseCase findRoleUseCase;

    public RoleModelService(CreateRoleUseCase createRoleUseCase, DeleteRoleUseCase deleteRoleUseCase, UpdateRoleUseCase updateRoleUseCase, FindRoleUseCase findRoleUseCase) {
        this.createRoleUseCase = createRoleUseCase;
        this.deleteRoleUseCase = deleteRoleUseCase;
        this.updateRoleUseCase = updateRoleUseCase;
        this.findRoleUseCase = findRoleUseCase;
    }

    @Override
    public Role create(Role role) {
        return createRoleUseCase.create(role);
    }

    @Override
    public Boolean delete(Long id) {
        return deleteRoleUseCase.delete(id);
    }

    @Override
    public Role logicalDeletion(Role role) {
        return deleteRoleUseCase.logicalDeletion(role);
    }

    @Override
    public Optional<Role> getById(Long id) {
        return findRoleUseCase.getById(id);
    }

    @Override
    public Optional<Role> getByName(String name) {
        return findRoleUseCase.getByName(name);
    }

    @Override
    public List<Role> getAll() {
        return findRoleUseCase.getAll();
    }

    @Override
    public Role update(Role role) {
        return updateRoleUseCase.update(role);
    }
}
