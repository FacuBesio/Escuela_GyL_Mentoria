package com.school.app.infrastructure.cotrollers;

import com.school.app.application.dto.school.SchoolDTORequest;
import com.school.app.application.dto.school.SchoolDTOResponse;
import com.school.app.application.services.school.SchoolService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/schools")
@Tag(name = "School", description = "Controller for School" )
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping
    public ResponseEntity<SchoolDTOResponse> create(@Valid @RequestBody SchoolDTORequest schoolDto) {
        return new ResponseEntity<>(schoolService.create(schoolDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<SchoolDTOResponse> getAll() {
        return schoolService.getAll();
    }

    @GetMapping("/{id}")
    public SchoolDTOResponse getById(@PathVariable("id") Long schoolId) {
        return schoolService.getById(schoolId);
    }

    @GetMapping("/getByUser/{username}")
    public SchoolDTOResponse getByUsername(@PathVariable("username") String username) {
        return schoolService.getByUser(username);
    }

    @PutMapping("/{id}")
    public SchoolDTOResponse update(@PathVariable("id") Long schoolId, @RequestBody SchoolDTORequest schoolToUpdate) {
        return schoolService.update(schoolId, schoolToUpdate);
    }

    @DeleteMapping("/{id}")
    public SchoolDTOResponse delete(@PathVariable("id") Long schoolId) {
        return schoolService.delete(schoolId);
    }

    @DeleteMapping("/logicalDeletion/{id}")
    public SchoolDTOResponse logicalDeletion(@PathVariable("id") Long schoolId) {
        return schoolService.logicalDeletion(schoolId);
    }

}
