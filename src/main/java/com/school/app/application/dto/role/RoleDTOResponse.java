package com.school.app.application.dto.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDTOResponse {
    private Long id;
    private String name;
    private Boolean enabled;
}
