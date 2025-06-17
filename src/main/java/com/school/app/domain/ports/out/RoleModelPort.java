package com.school.app.domain.ports.out;


import com.school.app.domain.models.Role;

import java.util.List;
import java.util.Optional;


public interface RoleModelPort {

    Role save(Role role);

    List<Role> findAll();

    Optional<Role> findById(Long id);

    Optional<Role> findByName(String name);

    Role update(Role role);

    Boolean delete(Long id);

    Role logicalDeletion(Role role);
}
