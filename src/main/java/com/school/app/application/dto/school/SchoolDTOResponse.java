package com.school.app.application.dto.school;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchoolDTOResponse {
    private Long id;
    private String name;
    private String address;
    private String username;
    private Boolean enabled;
}
