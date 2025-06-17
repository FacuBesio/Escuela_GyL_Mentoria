package com.school.app.infrastructure.adapters;

import com.school.app.domain.models.Course_User;
import com.school.app.domain.ports.out.Course_UserModelPort;
import com.school.app.infrastructure.entities.Course_UserEntity;
import com.school.app.infrastructure.exceptions.GenericErrorException;
import com.school.app.infrastructure.mappers.Course_UserDomainMapper;
import com.school.app.infrastructure.repositories.JpaCourse_UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Course_UserModelAdapter implements Course_UserModelPort {

    private final JpaCourse_UserRepository jpaCourse_UserRepository;
    private final Course_UserDomainMapper course_userDomainMapper;

    public Course_UserModelAdapter(JpaCourse_UserRepository jpaCourse_UserRepository, Course_UserDomainMapper course_userDomainMapper) {
        this.jpaCourse_UserRepository = jpaCourse_UserRepository;
        this.course_userDomainMapper = course_userDomainMapper;
    }

    @Override
    public Course_User save(Course_User course_user) {
        try {
            Course_UserEntity course_userEntity = course_userDomainMapper.fromDomainModel(course_user);
            Course_UserEntity newCourse_UserEntity = jpaCourse_UserRepository.save(course_userEntity);
            return course_userDomainMapper.toDomainModel(newCourse_UserEntity);
        } catch (GenericErrorException e) {
            throw e;
        } catch (Exception e) {
            throw new GenericErrorException(e.getMessage());
        }
    }

    @Override
    public List<Course_User> findAll() {
        List<Course_User> course_users = jpaCourse_UserRepository.findAll()
                .stream()
                .map((course_userEntity) -> course_userDomainMapper.toDomainModel(course_userEntity))
                .collect(Collectors.toList());

        return course_users;
    }

    @Override
    public Optional<Course_User> findById(Long id) {
        Optional<Course_User> course_user = jpaCourse_UserRepository.findById(id)
                .map((course_userEntity) -> course_userDomainMapper.toDomainModel(course_userEntity));
        return course_user;
    }

    @Override
    public Course_User update(Course_User course_user) {
        Course_UserEntity course_userEntity = course_userDomainMapper.fromDomainModel(course_user);
        Course_UserEntity updatedCourse_UserEntity = jpaCourse_UserRepository.save(course_userEntity);
        return course_userDomainMapper.toDomainModel(updatedCourse_UserEntity);

    }

    @Override
    public Boolean delete(Long id) {
        jpaCourse_UserRepository.deleteById(id);
        return true;
    }

    @Override
    public Course_User logicalDeletion(Course_User course_user) {
        Course_UserEntity course_userEntity = course_userDomainMapper.fromDomainModel(course_user);
        Course_UserEntity updatedCourse_UserEntity = jpaCourse_UserRepository.save(course_userEntity);
        return course_userDomainMapper.toDomainModel(updatedCourse_UserEntity);
    }

}
