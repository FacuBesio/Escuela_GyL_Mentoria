package com.school.app.application.dto.role;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDTORequest {

    @NotBlank(message = "Name cannot be empty.")
    private String name;

    private Boolean enabled = true;
}
