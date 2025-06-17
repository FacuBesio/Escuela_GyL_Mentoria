package com.school.app.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class School {
    private Long id;
    private String name;
    private String address;
    private String username;
    private String password;
    private Boolean enabled;
}
