package com.school.app.infrastructure.adapters;


import com.school.app.domain.models.User;
import com.school.app.domain.ports.out.UserModelPort;
import com.school.app.infrastructure.entities.UserEntity;
import com.school.app.infrastructure.exceptions.GenericErrorException;
import com.school.app.infrastructure.mappers.UserDomainMapper;
import com.school.app.infrastructure.repositories.JpaSchoolRepository;
import com.school.app.infrastructure.repositories.JpaUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserModelAdapter implements UserModelPort {

    private final JpaUserRepository jpaUserRepository;
    private final JpaSchoolRepository jpaSchoolRepository;
    private final UserDomainMapper userDomainMapper;

    public UserModelAdapter(JpaUserRepository jpaUserRepository, JpaSchoolRepository jpaSchoolRepository, UserDomainMapper userDomainMapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.jpaSchoolRepository = jpaSchoolRepository;
        this.userDomainMapper = userDomainMapper;
    }

    @Override
    @Transactional
    public User save(User user) {
        try {
            if (jpaUserRepository.existsByUsername(user.getUsername()) ||
                    jpaSchoolRepository.existsByUsername(user.getUsername())) {
                throw new GenericErrorException("Username is already in use");
            }
            UserEntity userEntity = userDomainMapper.fromDomainModel(user);
            UserEntity newUserEntity = jpaUserRepository.save(userEntity);
            return userDomainMapper.toDomainModel(newUserEntity);
        } catch (Exception e) {
            throw new GenericErrorException(e.getMessage());
        }
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        Page<UserEntity> usersEntity = jpaUserRepository.findAll(pageable);
        return usersEntity.map(userDomainMapper::toDomainModel);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id)
                .map(userDomainMapper::toDomainModel);
    }

    @Override
    public Optional<User> findByUser(String username) {
        return jpaUserRepository.findByUsername(username)
                .map(userDomainMapper::toDomainModel);
    }

    @Override
    public List<User> findBySchoolId(Long schoolId) {
        List<UserEntity> users = jpaUserRepository.findBySchoolId(schoolId);
        return users.stream().map(userDomainMapper::toDomainModel).toList();
    }

    @Override
    public List<User> findByCompanyIdAndHasNoSquad(Long schoolId) {
//        List<UserEntity> users = jpaUserRepository.findByCompanyIdAndHasNoSquad(schoolId);
//        return users.stream().map(userDomainMapper::toDomainModel).toList();
        return  null;
    }

    @Override
    public Page<User> filterUsersByParams(Pageable pageable,
                                          String usernameBrowser,
                                          String roleBrowser,
                                          Boolean enabledBrowser) {
        Page<UserEntity> usersEntity = jpaUserRepository.filterUsersByParams(pageable, usernameBrowser, roleBrowser, enabledBrowser);
        return usersEntity.map(userDomainMapper::toDomainModel);
    }

    @Override
    @Transactional
    public User update(User user) {
        if (jpaSchoolRepository.existsByUsername(user.getUsername())) {
            throw new GenericErrorException("Username is already in use");
        }
        UserEntity userEntity = userDomainMapper.fromDomainModel(user);
        UserEntity updatedUserEntity = jpaUserRepository.save(userEntity);
        return userDomainMapper.toDomainModel(updatedUserEntity);

    }

    @Override
    public Boolean delete(Long id) {
        jpaUserRepository.deleteById(id);
        return true;
    }

    @Override
    public User logicalDeletion(User user) {
        UserEntity userEntity = userDomainMapper.fromDomainModel(user);
        UserEntity updatedUserEntity = jpaUserRepository.save(userEntity);
        return userDomainMapper.toDomainModel(updatedUserEntity);
    }
}
