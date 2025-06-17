package com.school.app.application.services.school.impl;

import com.school.app.domain.models.School;
import com.school.app.domain.ports.in.school.CreateSchoolUseCase;
import com.school.app.domain.ports.in.school.DeleteSchoolUseCase;
import com.school.app.domain.ports.in.school.FindSchoolUseCase;
import com.school.app.domain.ports.in.school.UpdateSchoolUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolModelService implements CreateSchoolUseCase, DeleteSchoolUseCase, UpdateSchoolUseCase, FindSchoolUseCase {

    private final CreateSchoolUseCase createSchoolUseCase;
    private final DeleteSchoolUseCase deleteSchoolUseCase;
    private final UpdateSchoolUseCase updateSchoolUseCase;
    private final FindSchoolUseCase findSchoolUseCase;

    public SchoolModelService(CreateSchoolUseCase createSchoolUseCase, DeleteSchoolUseCase deleteSchoolUseCase, UpdateSchoolUseCase updateSchoolUseCase, FindSchoolUseCase findSchoolUseCase) {
        this.createSchoolUseCase = createSchoolUseCase;
        this.deleteSchoolUseCase = deleteSchoolUseCase;
        this.updateSchoolUseCase = updateSchoolUseCase;
        this.findSchoolUseCase = findSchoolUseCase;
    }

    @Override
    public School create(School school) {
        return createSchoolUseCase.create(school);
    }

    @Override
    public Boolean delete(Long id) {
        return deleteSchoolUseCase.delete(id);
    }

    @Override
    public School logicalDeletion(School school) {
        return deleteSchoolUseCase.logicalDeletion(school);
    }

    @Override
    public Optional<School> getById(Long id) {
        return findSchoolUseCase.getById(id);
    }

    @Override
    public Optional<School> getByUsername(String username) {
        return findSchoolUseCase.getByUsername(username);
    }

    @Override
    public List<School> getAll() {
        return findSchoolUseCase.getAll();
    }

    @Override
    public School update(School school) {
        return updateSchoolUseCase.update(school);
    }
}
