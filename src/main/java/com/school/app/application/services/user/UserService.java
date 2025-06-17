package com.school.app.application.services.user;


import com.school.app.application.dto.user.UserDTORequest;
import com.school.app.application.dto.user.UserDTOResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    UserDTOResponse create(UserDTORequest userDto);

    Page<UserDTOResponse> getAll(int page,
                                 int size,
                                 String sortDirection,
                                 String sortColumn,
                                 String usernameBrowser,
                                 String roleBrowser,
                                 Boolean enabledBrowser);

    UserDTOResponse getById(Long id);

    UserDTOResponse getByUsername(String username);

    UserDTOResponse update(Long userId, UserDTORequest userDtoToUpdate);

    UserDTOResponse delete(Long id);

    UserDTOResponse logicalDeletion(Long id);

    List<UserDTOResponse> getBySchoolId(Long schoolId);

    List<UserDTOResponse> getByCompanyIdAndHasNoSquad(Long companyId);
}
