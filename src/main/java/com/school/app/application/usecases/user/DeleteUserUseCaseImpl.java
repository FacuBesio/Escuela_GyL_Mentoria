package com.school.app.application.usecases.user;

import com.school.app.domain.models.User;
import com.school.app.domain.ports.in.user.DeleteUserUseCase;
import com.school.app.domain.ports.out.UserModelPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserModelPort userModelPort;

    public DeleteUserUseCaseImpl(UserModelPort userModelPort) {
        this.userModelPort = userModelPort;
    }

    @Override
    public Boolean delete(Long id) {
        return userModelPort.delete(id);
    }

    @Override
    public User logicalDeletion(User user) {
        return userModelPort.logicalDeletion(user);
    }
}
