package com.school.app.domain.ports.in.school;

import com.school.app.domain.models.School;

public interface UpdateSchoolUseCase {

    School update(School school);
}
