package com.school.app.infrastructure.cotrollers;


import com.school.app.application.dto.user.UserDTORequest;
import com.school.app.application.dto.user.UserDTOResponse;
import com.school.app.application.services.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "Controller for User")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTOResponse> create(@Valid @RequestBody UserDTORequest userDTORequest) {
        return new ResponseEntity<>(userService.create(userDTORequest), HttpStatus.CREATED);
    }

    @GetMapping
    public Page<UserDTOResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String sortDirection,
            @RequestParam(defaultValue = "") String sortColumn,
            @RequestParam(required = false) String usernameBrowser,
            @RequestParam(required = false) String roleBrowser,
            @RequestParam(required = false) Boolean enabledBrowser) {
        return userService.getAll(page, size, sortDirection, sortColumn, usernameBrowser, roleBrowser, enabledBrowser);
    }

    @GetMapping("/{id}")
    public UserDTOResponse getById(@PathVariable("id") Long userId) {
        return userService.getById(userId);
    }

    @GetMapping("/getByUsername/{user}")
    public UserDTOResponse getByUsername(@PathVariable("user") String username) {
        return userService.getByUsername(username);
    }

    @GetMapping("/getBySchoolId/{schoolId}")
    public List<UserDTOResponse> getBySchoolId(@PathVariable("schoolId") Long schoolId) {
        return userService.getBySchoolId(schoolId);
    }

    @PutMapping("/{id}")
    public UserDTOResponse update(@PathVariable("id") Long userId, @RequestBody UserDTORequest userDTORequest) {
        return userService.update(userId, userDTORequest);
    }

    @DeleteMapping("/{id}")
    public UserDTOResponse delete(@PathVariable("id") Long userId) {
        return userService.delete(userId);
    }

    @DeleteMapping("/logicalDeletion/{id}")
    public UserDTOResponse logicalDeletion(@PathVariable("id") Long userId) {
        return userService.logicalDeletion(userId);
    }

}
