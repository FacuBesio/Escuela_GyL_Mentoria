package com.school.app.infrastructure.cotrollers;

import com.school.app.application.dto.course.CourseDTORequest;
import com.school.app.application.dto.course.CourseDTOResponse;
import com.school.app.application.services.course.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/courses")
@Tag(name = "Course", description = "Controller for Course" )
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseDTOResponse> create(@Valid @RequestBody CourseDTORequest courseDto) {
        return new ResponseEntity<>(courseService.create(courseDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<CourseDTOResponse> getAll() {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public CourseDTOResponse getById(@PathVariable("id") Long courseId) {
        return courseService.getById(courseId);
    }

    @GetMapping("/getByName/{name}")
    public CourseDTOResponse getByName(@PathVariable("name") String name) {
        return courseService.getByName(name);
    }

    @PutMapping("/{id}")
    public CourseDTOResponse update(@PathVariable("id") Long courseId, @RequestBody CourseDTORequest courseToUpdate) {
        return courseService.update(courseId, courseToUpdate);
    }

    @DeleteMapping("/{id}")
    public CourseDTOResponse delete(@PathVariable("id") Long courseId) {
        return courseService.delete(courseId);
    }

    @DeleteMapping("/logicalDeletion/{id}")
    public CourseDTOResponse logicalDeletion(@PathVariable("id") Long courseId) {
        return courseService.logicalDeletion(courseId);
    }

}
