package com.school.app.application.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDTORequest {

    @NotNull(message = "School is required. An ID must be provided.")
    private Long school_id;

    @NotBlank(message = "Name cannot be empty.")
    private String name;

    private String description;

    private Boolean enabled = true;
}
