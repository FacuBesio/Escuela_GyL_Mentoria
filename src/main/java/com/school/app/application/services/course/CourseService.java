package com.school.app.application.services.course;

import com.school.app.application.dto.course.CourseDTORequest;
import com.school.app.application.dto.course.CourseDTOResponse;

import java.util.List;

public interface CourseService {

    CourseDTOResponse create(CourseDTORequest courseDto);

    List<CourseDTOResponse> getAll();

    CourseDTOResponse getById(Long id);

    CourseDTOResponse getByName(String name);

    CourseDTOResponse update(Long courseId, CourseDTORequest courseDtoToUpdate);

    CourseDTOResponse delete(Long id);

    CourseDTOResponse logicalDeletion(Long id);
}
