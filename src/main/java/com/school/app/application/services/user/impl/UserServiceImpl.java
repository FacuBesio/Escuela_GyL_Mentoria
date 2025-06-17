package com.school.app.application.services.user.impl;

import com.school.app.application.dto.user.UserDTORequest;
import com.school.app.application.dto.user.UserDTOResponse;
import com.school.app.application.mappers.UserDTOMapper;
import com.school.app.application.services.user.UserService;
import com.school.app.domain.models.User;
import com.school.app.infrastructure.exceptions.GenericNoContentException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserModelService userModelService;
    private final UserDTOMapper userDTOMapper;


    public UserServiceImpl(UserModelService userModelService, UserDTOMapper userDTOMapper) {
        this.userModelService = userModelService;
        this.userDTOMapper = userDTOMapper;
    }

    @Override
    @Transactional
    public UserDTOResponse create(UserDTORequest userDTORequest) {
        String defaultPassword = "password123";
        userDTORequest.setEnabled(true);
        User user = userDTOMapper.toModel(userDTORequest);
        user.setPassword(defaultPassword);
        User newUser = userModelService.create(user);
        return userDTOMapper.toDto(newUser);
    }

    @Override
    public Page<UserDTOResponse> getAll(int page,
                                        int size,
                                        String sortDirection,
                                        String sortColumn,
                                        String usernameBrowser,
                                        String roleBrowser,
                                        Boolean enabledBrowser) {
        Pageable pageable;

        if (sortColumn != "" && sortDirection != "") {
            Sort.Direction direction = sortDirection.equalsIgnoreCase("desc")
                    ? Sort.Direction.DESC
                    : Sort.Direction.ASC;
            pageable = PageRequest.of(page, size, Sort.by(direction, sortColumn));
        } else {
            pageable = PageRequest.of(page, size);
        }

        boolean hasFilters = (usernameBrowser != null && usernameBrowser != "") ||
                (roleBrowser != null && roleBrowser != "") ||
                (enabledBrowser != null);

        Page<User> users = hasFilters
                ? userModelService.filterUsersByParams(pageable, usernameBrowser, roleBrowser, enabledBrowser)
                : userModelService.getAll(pageable);

        if (users == null || users.isEmpty()) {
            throw new GenericNoContentException("No users were found in the database.");
        }
        return users.map(userDTOMapper::toDto);
    }


    @Override
    public UserDTOResponse getById(Long id) {
        User user = userModelService.getById(id)
                .orElseThrow(() -> new GenericNoContentException("User with id '" + id + "' not found."));
        return userDTOMapper.toDto(user);
    }

    @Override
    public UserDTOResponse getByUsername(String username) {
        User user = userModelService.getByUsername(username)
                .orElseThrow(() -> new GenericNoContentException("User with username '" + username + "' not found."));
        return userDTOMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserDTOResponse update(Long userId, UserDTORequest userDto) {
        User user = userModelService.getById(userId)
                .orElseThrow(() -> new GenericNoContentException("CANNOT UPDATE. User with id '" + userId + "' not found."));

        User userToUpdate = userDTOMapper.toModel(userDto);

        user.setSchool(userToUpdate.getSchool());
        user.setRole(userToUpdate.getRole());
        user.setName(userToUpdate.getName());
        user.setLastName(userToUpdate.getLastName());
        user.setMobile(userToUpdate.getMobile());
        user.setUsername(userToUpdate.getUsername());
        user.setEnabled(userToUpdate.getEnabled());
        User updatedUser = userModelService.update(user);
        return userDTOMapper.toDto(updatedUser);
    }


    @Override
    public UserDTOResponse delete(Long id) {
        User user = userModelService.getById(id)
                .orElseThrow(() -> new GenericNoContentException("CANNOT DELETE. User with id '" + id + "' not found."));
        Boolean isDelete = userModelService.delete(id);
        if (isDelete == true) {
            return userDTOMapper.toDto(user);
        }
        throw new GenericNoContentException("CANNOT DELETE. Something went wrong during the process. Try again.");
    }

    @Override
    public UserDTOResponse logicalDeletion(Long id) {
        User user = userModelService.getById(id)
                .orElseThrow(() -> new GenericNoContentException("CANNOT DISABLE. User with id '" + id + "' not found."));
        user.setEnabled(false);
        User disableUser = userModelService.logicalDeletion(user);
        return userDTOMapper.toDto(disableUser);
    }

    @Override
    public List<UserDTOResponse> getBySchoolId(Long schoolId) {
        List<User> users = userModelService.getBySchoolId(schoolId);

        if (users == null || users.isEmpty()) {
            throw new GenericNoContentException("No users were found in the database for schoolId: '" + schoolId + "' .");
        }
        return userDTOMapper.toDtoList(users);
    }

    @Override
    public List<UserDTOResponse> getByCompanyIdAndHasNoSquad(Long schoolId) {
        List<User> users = userModelService.getByCompanyIdAndHasNoSquad(schoolId);

        if (users == null || users.isEmpty()) {
            throw new GenericNoContentException("No users were found in the database for schoolId: '" + schoolId + "' .");
        }
        return userDTOMapper.toDtoList(users);
    }
}
