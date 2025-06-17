package com.school.app.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Long id;
    private School school;
    private Role role;
    private String name;
    private String lastName;
    private String mobile;
    private String username;
    private String password;
    private Boolean enabled;
    private List<Course> courses;
}
