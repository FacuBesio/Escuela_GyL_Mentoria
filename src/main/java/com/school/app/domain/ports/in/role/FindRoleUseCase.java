package com.school.app.domain.ports.in.role;

import com.school.app.domain.models.Role;
import java.util.List;
import java.util.Optional;

public interface FindRoleUseCase {

    Optional<Role> getById(Long id);
    Optional<Role> getByName(String name);
    List<Role> getAll();
}
