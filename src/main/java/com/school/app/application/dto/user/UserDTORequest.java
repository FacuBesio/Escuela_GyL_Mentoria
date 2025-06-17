package com.school.app.application.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTORequest {

    @NotNull(message = "School is required. An ID must be provided.")
    private Long school_id;

    @NotNull(message = "Role is required. An ID must be provided.")
    private Long role_id;

    @NotBlank(message = "Name cannot be empty.")
    private String name;

    @NotBlank(message = "LastName cannot be empty.")
    private String lastName;

    private String mobile;

    @NotBlank(message = "Username cannot be empty.")
    private String username;

    private Boolean enabled;
}
