package com.school.app.application.usecases.user;

import com.school.app.domain.models.User;
import com.school.app.domain.ports.in.user.UpdateUserUseCase;
import com.school.app.domain.ports.out.UserModelPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserModelPort userModelPort;

    public UpdateUserUseCaseImpl(UserModelPort userModelPort) {
        this.userModelPort = userModelPort;
    }

    @Override
    @Transactional
    public User update(User user) {
        return userModelPort.update(user);
    }
}
