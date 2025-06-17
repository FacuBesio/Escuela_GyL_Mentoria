package com.school.app.domain.ports.in.user;

import com.school.app.domain.models.User;

public interface DeleteUserUseCase {

   Boolean delete(Long id);

   User logicalDeletion(User user);
}
