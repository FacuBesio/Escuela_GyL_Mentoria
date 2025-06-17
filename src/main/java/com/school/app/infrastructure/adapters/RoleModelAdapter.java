package com.school.app.infrastructure.adapters;

import com.school.app.domain.models.Role;
import com.school.app.domain.ports.out.RoleModelPort;
import com.school.app.infrastructure.entities.RoleEntity;
import com.school.app.infrastructure.exceptions.GenericErrorException;
import com.school.app.infrastructure.mappers.RoleDomainMapper;
import com.school.app.infrastructure.repositories.JpaRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleModelAdapter implements RoleModelPort {

    private final JpaRoleRepository jpaRoleRepository;
    private final RoleDomainMapper roleDomainMapper;

    public RoleModelAdapter(JpaRoleRepository jpaRoleRepository, RoleDomainMapper roleDomainMapper) {
        this.jpaRoleRepository = jpaRoleRepository;
        this.roleDomainMapper = roleDomainMapper;
    }

    @Override
    public Role save(Role role) {
        try {
            if (jpaRoleRepository.existsByName(role.getName())) {
                throw new GenericErrorException("Name is already in use");
            }
            RoleEntity roleEntity = roleDomainMapper.fromDomainModel(role);
            RoleEntity newRoleEntity = jpaRoleRepository.save(roleEntity);
            return roleDomainMapper.toDomainModel(newRoleEntity);
        } catch (GenericErrorException e) {
            throw e;
        } catch (Exception e) {
            throw new GenericErrorException(e.getMessage());
        }
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = jpaRoleRepository.findAll()
                .stream()
                .map((roleEntity) -> roleDomainMapper.toDomainModel(roleEntity))
                .collect(Collectors.toList());

        return roles;
    }

    @Override
    public Optional<Role> findById(Long id) {
        Optional<Role> role = jpaRoleRepository.findById(id)
                .map((roleEntity) -> roleDomainMapper.toDomainModel(roleEntity));
        return role;
    }

    @Override
    public Optional<Role> findByName(String name) {
        RoleEntity role = jpaRoleRepository.findByName(name);
        return Optional.ofNullable(roleDomainMapper.toDomainModel(role));
    }

    @Override
    public Role update(Role role) {
        RoleEntity roleEntity = roleDomainMapper.fromDomainModel(role);
        RoleEntity updatedRoleEntity = jpaRoleRepository.save(roleEntity);
        return roleDomainMapper.toDomainModel(updatedRoleEntity);

    }

    @Override
    public Boolean delete(Long id) {
        jpaRoleRepository.deleteById(id);
        return true;
    }

    @Override
    public Role logicalDeletion(Role role) {
        RoleEntity roleEntity = roleDomainMapper.fromDomainModel(role);
        RoleEntity updatedRoleEntity = jpaRoleRepository.save(roleEntity);
        return roleDomainMapper.toDomainModel(updatedRoleEntity);
    }

}
