package com.school.app.domain.ports.in.user;

import com.school.app.domain.models.User;


public interface UpdateUserUseCase {

    User update(User user);
}
