package com.school.app.domain.ports.in.school;

import com.school.app.domain.models.School;

public interface DeleteSchoolUseCase {
    Boolean delete(Long id);

    School logicalDeletion(School school);
}
