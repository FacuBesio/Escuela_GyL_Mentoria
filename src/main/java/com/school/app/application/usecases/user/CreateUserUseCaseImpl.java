package com.school.app.application.usecases.user;

import com.school.app.domain.models.User;
import com.school.app.domain.ports.in.user.CreateUserUseCase;
import com.school.app.domain.ports.out.UserModelPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserModelPort userModelPort;

    public CreateUserUseCaseImpl(UserModelPort userModelPort) {
        this.userModelPort = userModelPort;
    }

    @Override
    @Transactional
    public User create(User user) {
        return userModelPort.save(user);
    }
}
