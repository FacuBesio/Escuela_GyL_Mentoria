package com.school.app.application.usecases.school;

import com.school.app.domain.models.School;
import com.school.app.domain.ports.in.school.FindSchoolUseCase;
import com.school.app.domain.ports.out.SchoolModelPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FindSchoolUseCaseImpl implements FindSchoolUseCase {

    private final SchoolModelPort schoolModelPort;

    public FindSchoolUseCaseImpl(SchoolModelPort schoolModelPort) {
        this.schoolModelPort = schoolModelPort;
    }

    @Override
    public List<School> getAll() {
        return schoolModelPort.findAll();
    }

    @Override
    public Optional<School> getById(Long id) {
        return schoolModelPort.findById(id);
    }

    @Override
    public Optional<School> getByUsername(String username)  {
        return schoolModelPort.findByUsername(username);
    }

}
