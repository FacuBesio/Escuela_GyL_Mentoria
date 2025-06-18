package com.school.app.application.dto.user;


import com.school.app.application.dto.course.CourseDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTOResponseSimplified {
    private Long id;
    private Long school_id;
    private String school;
    private Long role_id;
    private String role;
    private String name;
    private String lastName;
    private String mobile;
    private String username;
    private Boolean enabled;
}
