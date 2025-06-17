package com.school.app.domain.ports.out;

import com.school.app.domain.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface UserModelPort {

    User save(User user);

    Page<User> findAll(Pageable pageable);

    Optional<User> findById(Long id);

    Optional<User> findByUser(String username);

    List<User> findBySchoolId(Long schoolId);

    List<User> findByCompanyIdAndHasNoSquad(Long companyId);

    Page<User> filterUsersByParams(Pageable pageable,
                                   String usernameBrowser,
                                   String roleBrowser,
                                   Boolean enabledBrowser);

    User update(User user);

    Boolean delete(Long id);

    User logicalDeletion(User user);
}
