package com.school.app.application.services.school.impl;

import com.school.app.application.dto.school.SchoolDTORequest;
import com.school.app.application.dto.school.SchoolDTOResponse;
import com.school.app.application.mappers.SchoolDTOMapper;
import com.school.app.application.services.school.SchoolService;
import com.school.app.domain.models.School;
import com.school.app.infrastructure.exceptions.GenericErrorException;
import com.school.app.infrastructure.exceptions.GenericNoContentException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolModelService schoolModelService;
    private final SchoolDTOMapper schoolDTOMapper;

    public SchoolServiceImpl(SchoolModelService schoolModelService, SchoolDTOMapper schoolDTOMapper) {
        this.schoolModelService = schoolModelService;
        this.schoolDTOMapper = schoolDTOMapper;
    }

    @Override
    public SchoolDTOResponse create(SchoolDTORequest schoolDto) {
        schoolDto.setPassword(schoolDto.getPassword());
        School school = schoolDTOMapper.toModel(schoolDto);
        School newSchool = schoolModelService.create(school);
        return schoolDTOMapper.toDto(newSchool);
    }

    @Override
    public List<SchoolDTOResponse> getAll() {
        List<School> schools = schoolModelService.getAll();
        if (schools.isEmpty()) {
            throw new GenericNoContentException("No schools were found in the database.");
        }
        return schoolDTOMapper.toDtoList(schools);
    }

    @Override
    public SchoolDTOResponse getById(Long id) {
        School school = schoolModelService.getById(id)
                .orElseThrow(() -> new GenericNoContentException("School with id '" + id + "' not found."));
        return schoolDTOMapper.toDto(school);
    }

    @Override
    public SchoolDTOResponse getByUser(String username) {
        School school = schoolModelService.getByUsername(username)
                .orElseThrow(() -> new GenericNoContentException("School with username '" + username + "' not found."));
        return schoolDTOMapper.toDto(school);
    }

    @Override
    public SchoolDTOResponse update(Long schoolId, SchoolDTORequest schoolDto) {
        School school = schoolModelService.getById(schoolId)
                .orElseThrow(() -> new GenericErrorException("CANNOT UPDATE. School with id '" + schoolId + "' not found."));
        School schoolToUpdate = schoolDTOMapper.toModel(schoolDto);
        school.setName(schoolToUpdate.getName());
        school.setUsername(schoolToUpdate.getUsername());
        school.setPassword(schoolToUpdate.getPassword());
        school.setEnabled(schoolToUpdate.getEnabled());
        School updatedSchool = schoolModelService.update(school);
        return schoolDTOMapper.toDto(updatedSchool);
    }

    @Override
    public SchoolDTOResponse delete(Long id) {
        School school = schoolModelService.getById(id)
                .orElseThrow(() -> new GenericErrorException("CANNOT DELETE. School with id '" + id + "' not found."));
        Boolean isDelete = schoolModelService.delete(id);
        if (isDelete == true) {
            return schoolDTOMapper.toDto(school);
        }
        throw new GenericErrorException("CANNOT DELETE. Something went wrong during the process. Try again.");
    }

    @Override
    public SchoolDTOResponse logicalDeletion(Long id) {
        School school = schoolModelService.getById(id)
                .orElseThrow(() -> new GenericErrorException("CANNOT DISABLE. School with id '" + id + "' not found."));
        school.setEnabled(false);
        School disableSchool = schoolModelService.logicalDeletion(school);
        return schoolDTOMapper.toDto(disableSchool);
    }
}
