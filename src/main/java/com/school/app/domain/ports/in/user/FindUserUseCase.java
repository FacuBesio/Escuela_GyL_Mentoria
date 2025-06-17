package com.school.app.domain.ports.in.user;

import com.school.app.domain.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FindUserUseCase {

    Page<User> getAll(Pageable pageable);

    Optional<User> getById(Long id);

    Optional<User> getByUsername(String username);

    Page<User> filterUsersByParams(Pageable pageable,
                                   String usernameBrowser,
                                   String roleBrowser,
                                   Boolean enabledBrowser);

    List<User> getBySchoolId(Long schoolId);

    List<User> getByCompanyIdAndHasNoSquad(Long companyId);
}
