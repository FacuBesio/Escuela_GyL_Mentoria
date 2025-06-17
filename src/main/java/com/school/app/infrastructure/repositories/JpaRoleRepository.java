package com.school.app.infrastructure.repositories;

import com.school.app.infrastructure.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRoleRepository extends JpaRepository<RoleEntity, Long> {
    boolean existsByName(String name);
    RoleEntity findByName(String name);
}
