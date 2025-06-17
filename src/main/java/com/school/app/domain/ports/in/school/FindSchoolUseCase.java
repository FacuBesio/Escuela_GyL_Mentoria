package com.school.app.domain.ports.in.school;

import com.school.app.domain.models.School;

import java.util.List;
import java.util.Optional;

public interface FindSchoolUseCase {
    List<School> getAll();

    Optional<School> getById(Long id);

    Optional<School> getByUsername(String username);
}
