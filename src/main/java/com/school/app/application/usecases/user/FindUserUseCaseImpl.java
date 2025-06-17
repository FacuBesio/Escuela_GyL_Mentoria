package com.school.app.application.usecases.user;

import com.school.app.domain.models.User;
import com.school.app.domain.ports.in.user.FindUserUseCase;
import com.school.app.domain.ports.out.UserModelPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FindUserUseCaseImpl implements FindUserUseCase {

    private final UserModelPort userModelPort;

    public FindUserUseCaseImpl(UserModelPort userModelPort) {
        this.userModelPort = userModelPort;
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userModelPort.findAll(pageable);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userModelPort.findById(id);
    }

    @Override
    public Page<User> filterUsersByParams(Pageable pageable,
                                          String usernameBrowser,
                                          String roleBrowser,
                                          Boolean enabledBrowser) {
        return userModelPort.filterUsersByParams(pageable, usernameBrowser, roleBrowser, enabledBrowser);
    }

    @Override
    public List<User> getBySchoolId(Long schoolId) {
        return userModelPort.findBySchoolId(schoolId);
    }

    @Override
    public List<User> getByCompanyIdAndHasNoSquad(Long companyId) {
        return userModelPort.findByCompanyIdAndHasNoSquad(companyId);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userModelPort.findByUser(username);
    }
}
