package com.school.app.application.services.course.impl;

import com.school.app.application.dto.course.CourseDTORequest;
import com.school.app.application.dto.course.CourseDTOResponse;
import com.school.app.application.mappers.CourseDTOMapper;
import com.school.app.application.services.course.CourseService;
import com.school.app.domain.models.Course;
import com.school.app.infrastructure.exceptions.GenericErrorException;
import com.school.app.infrastructure.exceptions.GenericNoContentException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseModelService courseModelService;
    private final CourseDTOMapper courseDTOMapper;

    public CourseServiceImpl(CourseModelService courseModelService, CourseDTOMapper courseDTOMapper) {
        this.courseModelService = courseModelService;
        this.courseDTOMapper = courseDTOMapper;
    }

    @Override
    public CourseDTOResponse create(CourseDTORequest courseDto) {
        Course course = courseDTOMapper.toModel(courseDto);
        Course newCourse = courseModelService.create(course);
        return courseDTOMapper.toDto(newCourse);
    }

    @Override
    public List<CourseDTOResponse> getAll() {
        List<Course> courses = courseModelService.getAll();
        if (courses.isEmpty()) {
            throw new GenericNoContentException("No courses were found in the database.");
        }
        return courseDTOMapper.toDtoList(courses);
    }

    @Override
    public CourseDTOResponse getById(Long id) {
        Course course = courseModelService.getById(id)
                .orElseThrow(() -> new GenericNoContentException("Course with id '" + id + "' not found."));
        return courseDTOMapper.toDto(course);
    }

    @Override
    public CourseDTOResponse getByName(String name) {
        Course course = courseModelService.getByName(name)
                .orElseThrow(() -> new GenericNoContentException("Course with name '" + name + "' not found."));
        return courseDTOMapper.toDto(course);
    }

    @Override
    public CourseDTOResponse update(Long courseId, CourseDTORequest courseDto) {
        Course course = courseModelService.getById(courseId)
                .orElseThrow(() -> new GenericErrorException("CANNOT UPDATE. Course with id '" + courseId + "' not found."));
        Course courseToUpdate = courseDTOMapper.toModel(courseDto);
        course.setSchool(courseToUpdate.getSchool());
        course.setName(courseToUpdate.getName());
        course.setDescription(courseToUpdate.getDescription());
        course.setEnabled(courseToUpdate.getEnabled());
        Course updatedCourse = courseModelService.update(course);
        return courseDTOMapper.toDto(updatedCourse);
    }

    @Override
    public CourseDTOResponse delete(Long id) {
        Course course = courseModelService.getById(id)
                .orElseThrow(() -> new GenericErrorException("CANNOT DELETE. Course with id '" + id + "' not found."));
        Boolean isDelete = courseModelService.delete(id);
        if (isDelete == true) {
            return courseDTOMapper.toDto(course);
        }
        throw new GenericErrorException("CANNOT DELETE. Something went wrong during the process. Try again.");
    }

    @Override
    public CourseDTOResponse logicalDeletion(Long id) {
        Course course = courseModelService.getById(id)
                .orElseThrow(() -> new GenericErrorException("CANNOT DISABLE. Course with id '" + id + "' not found."));
        course.setEnabled(false);
        Course disableCourse = courseModelService.logicalDeletion(course);
        return courseDTOMapper.toDto(disableCourse);
    }
}
