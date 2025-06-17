package com.school.app.application.services.user.impl;

import com.school.app.domain.models.User;
import com.school.app.domain.ports.in.user.CreateUserUseCase;
import com.school.app.domain.ports.in.user.DeleteUserUseCase;
import com.school.app.domain.ports.in.user.FindUserUseCase;
import com.school.app.domain.ports.in.user.UpdateUserUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserModelService implements CreateUserUseCase, DeleteUserUseCase, UpdateUserUseCase, FindUserUseCase {

    private final CreateUserUseCase createUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final FindUserUseCase findUserUseCase;

    public UserModelService(CreateUserUseCase createUserUseCase, DeleteUserUseCase deleteUserUseCase, UpdateUserUseCase updateUserUseCase, FindUserUseCase findUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.findUserUseCase = findUserUseCase;
    }

    @Override
    @Transactional
    public User create(User user) {
        return createUserUseCase.create(user);
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return findUserUseCase.getAll(pageable);
    }

    @Override
    public Boolean delete(Long id) {
        return deleteUserUseCase.delete(id);
    }

    @Override
    public User logicalDeletion(User user) {
        return deleteUserUseCase.logicalDeletion(user);
    }

    @Override
    public Optional<User> getById(Long id) {
        return findUserUseCase.getById(id);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return findUserUseCase.getByUsername(username);
    }

    @Override
    public Page<User> filterUsersByParams(Pageable pageable,
                                          String usernameBrowser,
                                          String roleBrowser,
                                          Boolean enabledBrowser) {
        return findUserUseCase.filterUsersByParams(pageable, usernameBrowser, roleBrowser, enabledBrowser);
    }

    @Override
    public List<User> getBySchoolId(Long schoolId) {
        return findUserUseCase.getBySchoolId(schoolId);
    }

    @Override
    public List<User> getByCompanyIdAndHasNoSquad(Long companyId) {
        return findUserUseCase.getByCompanyIdAndHasNoSquad(companyId);
    }

    @Override
    @Transactional
    public User update(User user) {
        return updateUserUseCase.update(user);
    }
}
