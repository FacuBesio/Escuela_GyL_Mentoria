package com.school.app.application.dto.school;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchoolDTORequest {
    @NotBlank(message = "Name cannot be empty.")
    private String name;

    private String address;

    @NotBlank(message = "Username cannot be empty.")
    private String username;

    private String password;

    private Boolean enabled = true;
}
