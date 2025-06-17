package com.school.app.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {
    private Long id;
    private School school;
    private String name;
    private String description;
    private Boolean enabled;
}
