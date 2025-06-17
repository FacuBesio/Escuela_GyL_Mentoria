package com.school.app.application.dto.course_user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course_UserDTOResponse {
    private Long id;
    private String course_id;
    private String user_id;
    private Boolean enabled;
}
