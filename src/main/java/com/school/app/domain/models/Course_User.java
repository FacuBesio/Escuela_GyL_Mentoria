package com.school.app.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course_User {
    private Long id;
    private Course course;
    private User user;
    private Boolean enabled;
}
