package com.school.app.application.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDTOResponse {
    private Long id;
    private Long school_id;
    private String school;
    private String name;
    private String description;
    private Boolean enabled;
}
