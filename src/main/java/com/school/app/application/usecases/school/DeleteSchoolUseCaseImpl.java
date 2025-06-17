package com.school.app.application.usecases.school;

import com.school.app.domain.models.School;
import com.school.app.domain.ports.in.school.DeleteSchoolUseCase;
import com.school.app.domain.ports.out.SchoolModelPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteSchoolUseCaseImpl implements DeleteSchoolUseCase {

    private final SchoolModelPort schoolModelPort;

    public DeleteSchoolUseCaseImpl(SchoolModelPort schoolModelPort) {
        this.schoolModelPort = schoolModelPort;
    }

    @Override
    public Boolean delete(Long id) {
        return schoolModelPort.delete(id);
    }

    @Override
    public School logicalDeletion(School school) {
        return schoolModelPort.logicalDeletion(school);
    }
}
