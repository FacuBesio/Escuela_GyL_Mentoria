package com.school.app.infrastructure.cotrollers;


import com.school.app.application.dto.role.RoleDTORequest;
import com.school.app.application.dto.role.RoleDTOResponse;
import com.school.app.application.services.role.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/roles")
@Tag(name = "Role", description = "Controller for Role" )
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleDTOResponse> create(@Valid @RequestBody RoleDTORequest roleDto) {
        return new ResponseEntity<>(roleService.create(roleDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<RoleDTOResponse> getAll() {
        return roleService.getAll();
    }

    @GetMapping("/{id}")
    public RoleDTOResponse getById(@PathVariable("id") Long roleId) {
        return roleService.getById(roleId);
    }

    @GetMapping("/getByName/{name}")
    public RoleDTOResponse getByName(@PathVariable("name") String name) {
        return roleService.getByName(name);
    }

    @PutMapping("/{id}")
    public RoleDTOResponse update(@PathVariable("id") Long roleId, @RequestBody RoleDTORequest roleToUpdate) {
        return roleService.update(roleId, roleToUpdate);
    }

    @DeleteMapping("/{id}")
    public RoleDTOResponse delete(@PathVariable("id") Long roleId) {
        return roleService.delete(roleId);
    }

    @DeleteMapping("/logicalDeletion/{id}")
    public RoleDTOResponse logicalDeletion(@PathVariable("id") Long roleId) {
        return roleService.logicalDeletion(roleId);
    }

}
