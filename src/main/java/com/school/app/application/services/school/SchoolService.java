package com.school.app.application.services.school;

import com.school.app.application.dto.school.SchoolDTORequest;
import com.school.app.application.dto.school.SchoolDTOResponse;

import java.util.List;

public interface SchoolService {

    SchoolDTOResponse create(SchoolDTORequest schoolDto);

    List<SchoolDTOResponse> getAll();

    SchoolDTOResponse getById(Long id);

    SchoolDTOResponse getByUser(String user);

    SchoolDTOResponse update(Long schoolId, SchoolDTORequest schoolDtoToUpdate);

    SchoolDTOResponse delete(Long id);

    SchoolDTOResponse logicalDeletion(Long id);
}
