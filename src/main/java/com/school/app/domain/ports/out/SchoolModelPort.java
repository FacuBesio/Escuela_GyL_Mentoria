package com.school.app.domain.ports.out;

import com.school.app.domain.models.School;

import java.util.List;
import java.util.Optional;


public interface SchoolModelPort {

    School save(School school);

    List<School> findAll();

    Optional<School> findById(Long id);

    Optional<School> findByUsername(String username);

    School update(School school);

    Boolean delete(Long id);

    School logicalDeletion(School school);
}
