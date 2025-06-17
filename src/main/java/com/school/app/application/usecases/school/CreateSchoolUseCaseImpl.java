package com.school.app.application.usecases.school;

import com.school.app.domain.models.School;
import com.school.app.domain.ports.in.school.CreateSchoolUseCase;
import com.school.app.domain.ports.out.SchoolModelPort;
import org.springframework.stereotype.Service;

@Service
public class CreateSchoolUseCaseImpl implements CreateSchoolUseCase {

    private final SchoolModelPort schoolModelPort;

    public CreateSchoolUseCaseImpl(SchoolModelPort schoolModelPort) {
        this.schoolModelPort = schoolModelPort;
    }

    @Override
    public School create(School school) {
        return schoolModelPort.save(school);
    }
}
