package com.school.app.application.dto.course_user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course_UserDTORequest {

    @NotNull(message = "Course is required. An ID must be provided.")
    private Long course_id;

    @NotNull(message = "User is required. An ID must be provided.")
    private Long user_id;

    private Boolean enabled = true;
}
