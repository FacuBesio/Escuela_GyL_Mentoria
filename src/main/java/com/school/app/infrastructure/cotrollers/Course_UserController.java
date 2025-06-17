package com.school.app.infrastructure.cotrollers;


import com.school.app.application.dto.course_user.Course_UserDTORequest;
import com.school.app.application.dto.course_user.Course_UserDTOResponse;
import com.school.app.application.services.course_user.Course_UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/course_users")
@Tag(name = "Course_User", description = "Controller for Course_User")
public class Course_UserController {
    private final Course_UserService course_userService;

    public Course_UserController(Course_UserService course_userService) {
        this.course_userService = course_userService;
    }

    @PostMapping
    public ResponseEntity<Course_UserDTOResponse> create(@Valid @RequestBody Course_UserDTORequest course_userDto) {
        return new ResponseEntity<>(course_userService.create(course_userDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Course_UserDTOResponse> getAll() {
        return course_userService.getAll();
    }

    @GetMapping("/{id}")
    public Course_UserDTOResponse getById(@PathVariable("id") Long course_userId) {
        return course_userService.getById(course_userId);
    }

    @PutMapping("/{id}")
    public Course_UserDTOResponse update(@PathVariable("id") Long course_userId, @RequestBody Course_UserDTORequest course_userToUpdate) {
        return course_userService.update(course_userId, course_userToUpdate);
    }

    @DeleteMapping("/{id}")
    public Course_UserDTOResponse delete(@PathVariable("id") Long course_userId) {
        return course_userService.delete(course_userId);
    }

    @DeleteMapping("/logicalDeletion/{id}")
    public Course_UserDTOResponse logicalDeletion(@PathVariable("id") Long course_userId) {
        return course_userService.logicalDeletion(course_userId);
    }

}
