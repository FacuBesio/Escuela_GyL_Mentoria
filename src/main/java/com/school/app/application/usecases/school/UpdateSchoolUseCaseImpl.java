package com.school.app.application.usecases.school;

import com.school.app.domain.models.School;
import com.school.app.domain.ports.in.school.UpdateSchoolUseCase;
import com.school.app.domain.ports.out.SchoolModelPort;
import org.springframework.stereotype.Service;

@Service
public class UpdateSchoolUseCaseImpl implements UpdateSchoolUseCase {

    private final SchoolModelPort schoolModelPort;

    public UpdateSchoolUseCaseImpl(SchoolModelPort schoolModelPort) {
        this.schoolModelPort = schoolModelPort;
    }

    @Override
    public School update(School school) {
        return schoolModelPort.update(school);
    }
}
