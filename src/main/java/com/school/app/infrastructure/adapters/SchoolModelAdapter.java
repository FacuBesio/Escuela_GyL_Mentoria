package com.school.app.infrastructure.adapters;

import com.school.app.domain.models.School;
import com.school.app.domain.ports.out.SchoolModelPort;
import com.school.app.infrastructure.entities.SchoolEntity;
import com.school.app.infrastructure.exceptions.GenericErrorException;
import com.school.app.infrastructure.mappers.SchoolDomainMapper;
import com.school.app.infrastructure.repositories.JpaSchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolModelAdapter implements SchoolModelPort {

    private final JpaSchoolRepository jpaSchoolRepository;
    private final SchoolDomainMapper schoolDomainMapper;

    public SchoolModelAdapter(JpaSchoolRepository jpaSchoolRepository, SchoolDomainMapper schoolDomainMapper) {
        this.jpaSchoolRepository = jpaSchoolRepository;
        this.schoolDomainMapper = schoolDomainMapper;
    }

    @Override
    public School save(School school) {
        try {
            if (jpaSchoolRepository.existsByName(school.getName())) {
                throw new GenericErrorException("Name is already in use");
            }
            if (jpaSchoolRepository.existsByUsername(school.getUsername())) {
                throw new GenericErrorException("Username is already in use");
            }
            SchoolEntity schoolEntity = schoolDomainMapper.fromDomainModel(school);
            SchoolEntity newSchoolEntity = jpaSchoolRepository.save(schoolEntity);
            return schoolDomainMapper.toDomainModel(newSchoolEntity);
        } catch (GenericErrorException e) {
            throw e;
        } catch (Exception e) {
            throw new GenericErrorException(e.getMessage());
        }
    }

    @Override
    public List<School> findAll() {
        List<School> schools = jpaSchoolRepository.findAll()
                .stream()
                .map((schoolEntity) -> schoolDomainMapper.toDomainModel(schoolEntity))
                .collect(Collectors.toList());
        return schools;
    }

    @Override
    public Optional<School> findById(Long id) {
        return jpaSchoolRepository.findById(id)
                .map(schoolDomainMapper::toDomainModel);
    }

    @Override
    public Optional<School> findByUsername(String username) {
        return jpaSchoolRepository.findByUsername(username)
                .map(schoolDomainMapper::toDomainModel);
    }

    @Override
    public School update(School school) {
        SchoolEntity schoolEntity = schoolDomainMapper.fromDomainModel(school);
        SchoolEntity updatedSchoolEntity = jpaSchoolRepository.save(schoolEntity);
        return schoolDomainMapper.toDomainModel(updatedSchoolEntity);

    }

    @Override
    public Boolean delete(Long id) {
        jpaSchoolRepository.deleteById(id);
        return true;
    }

    @Override
    public School logicalDeletion(School school) {
        SchoolEntity schoolEntity = schoolDomainMapper.fromDomainModel(school);
        SchoolEntity updatedSchoolEntity = jpaSchoolRepository.save(schoolEntity);
        return schoolDomainMapper.toDomainModel(updatedSchoolEntity);
    }
}
