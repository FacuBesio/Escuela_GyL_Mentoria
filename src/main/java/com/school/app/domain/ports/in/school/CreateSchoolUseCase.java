package com.school.app.domain.ports.in.school;

import com.school.app.domain.models.School;

public interface CreateSchoolUseCase {
    School create(School school);
}
