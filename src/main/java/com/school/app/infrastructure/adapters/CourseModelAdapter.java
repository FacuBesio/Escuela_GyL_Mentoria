package com.school.app.infrastructure.adapters;

import com.school.app.domain.models.Course;
import com.school.app.domain.ports.out.CourseModelPort;
import com.school.app.infrastructure.entities.CourseEntity;
import com.school.app.infrastructure.exceptions.GenericErrorException;
import com.school.app.infrastructure.mappers.CourseDomainMapper;
import com.school.app.infrastructure.repositories.JpaCourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseModelAdapter implements CourseModelPort {

    private final JpaCourseRepository jpaCourseRepository;
    private final CourseDomainMapper courseDomainMapper;

    public CourseModelAdapter(JpaCourseRepository jpaCourseRepository, CourseDomainMapper courseDomainMapper) {
        this.jpaCourseRepository = jpaCourseRepository;
        this.courseDomainMapper = courseDomainMapper;
    }

    @Override
    public Course save(Course course) {
        try {
            if (jpaCourseRepository.existsByName(course.getName())) {
                throw new GenericErrorException("Name is already in use");
            }
            CourseEntity courseEntity = courseDomainMapper.fromDomainModel(course);
            CourseEntity newCourseEntity = jpaCourseRepository.save(courseEntity);
            return courseDomainMapper.toDomainModel(newCourseEntity);
        } catch (GenericErrorException e) {
            throw e;
        } catch (Exception e) {
            throw new GenericErrorException(e.getMessage());
        }
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = jpaCourseRepository.findAll()
                .stream()
                .map((courseEntity) -> courseDomainMapper.toDomainModel(courseEntity))
                .collect(Collectors.toList());
        return courses;
    }

    @Override
    public Optional<Course> findById(Long id) {
        return jpaCourseRepository.findById(id)
                .map(courseDomainMapper::toDomainModel);
    }

    @Override
    public Optional<Course> findByName(String name) {
        return jpaCourseRepository.findByName(name)
                .map(courseDomainMapper::toDomainModel);
    }

    @Override
    public Course update(Course course) {
        CourseEntity courseEntity = courseDomainMapper.fromDomainModel(course);
        CourseEntity updatedCourseEntity = jpaCourseRepository.save(courseEntity);
        return courseDomainMapper.toDomainModel(updatedCourseEntity);

    }

    @Override
    public Boolean delete(Long id) {
        jpaCourseRepository.deleteById(id);
        return true;
    }

    @Override
    public Course logicalDeletion(Course course) {
        CourseEntity courseEntity = courseDomainMapper.fromDomainModel(course);
        CourseEntity updatedCourseEntity = jpaCourseRepository.save(courseEntity);
        return courseDomainMapper.toDomainModel(updatedCourseEntity);
    }
}
