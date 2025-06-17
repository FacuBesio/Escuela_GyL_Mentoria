package com.school.app.infrastructure.config;

import com.school.app.application.dto.course.CourseDTORequest;
import com.school.app.application.dto.course.CourseDTOResponse;
import com.school.app.application.dto.role.RoleDTORequest;
import com.school.app.application.dto.role.RoleDTOResponse;
import com.school.app.application.dto.school.SchoolDTORequest;
import com.school.app.application.dto.school.SchoolDTOResponse;
import com.school.app.application.services.course.CourseService;
import com.school.app.application.services.course_user.Course_UserService;
import com.school.app.application.services.role.RoleService;
import com.school.app.application.services.school.SchoolService;
import com.school.app.infrastructure.exceptions.GenericNoContentException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            SchoolService schoolService,
            CourseService courseService,
            RoleService roleService,
            Course_UserService course_userService
    ) {
        return args -> {

            // SCHOOL
            try {
                List<SchoolDTOResponse> listSchool = schoolService.getAll();
                if (listSchool.size() > 0) {
                    System.out.println("Schools already exist. Skipping school creation.");
                }
            } catch (GenericNoContentException e) {
                System.out.println("No schools found. Creating default school...");
                SchoolDTORequest schoolDTO = new SchoolDTORequest(
                        "G&L Group",
                        "Monteagudo 275, C1437 Cdad. Autónoma de Buenos Aires",
                        "masterSchool@gylgroup.com",
                        "password123",
                        true);
                SchoolDTOResponse newSchool = schoolService.create(schoolDTO);
                System.out.println(". School inserted into the database: " + newSchool);
            }

            // ROLE
            try {
                List<RoleDTOResponse> listRole = roleService.getAll();
                if (listRole.size() > 0) {
                    System.out.println("Roles already exist. Skipping role creation.");
                }
            } catch (GenericNoContentException e) {
                System.out.println("No roles found. Creating default roles...");
                List<String> roleNames = List.of(
                        "student",
                        "teacher"
                );
                roleNames.forEach(name -> {
                    RoleDTORequest roleDTO = new RoleDTORequest(name, true);
                    RoleDTOResponse newRole = roleService.create(roleDTO);
                    System.out.println(name);
                });
            }


            // COURSE
            try {
                List<CourseDTOResponse> listCourse = courseService.getAll();
                if (listCourse.size() > 0) {
                    System.out.println("Courses already exist. Skipping company creation.");
                }
            } catch (GenericNoContentException e) {
                System.out.println("No courses found. Creating default courses...");
                List<SchoolDTOResponse> listSchool = schoolService.getAll();
                if (listSchool.size() > 0) {
                    Long school_id = listSchool.get(0).getId();

                    CourseDTORequest companyDTO = new CourseDTORequest(
                            school_id,
                            "Curso 1",
                            "Descripción del curso 1.",
                            true);
                    CourseDTOResponse newCourse = courseService.create(companyDTO);
                    System.out.println(". Course inserted into the database: " + newCourse);
                    companyDTO = new CourseDTORequest(
                            school_id,
                            "Curso 2",
                            "Descripción del curso 2.",
                            true);
                    newCourse = courseService.create(companyDTO);
                    System.out.println(". Course inserted into the database: " + newCourse);
                    companyDTO = new CourseDTORequest(
                            school_id,
                            "Curso 3",
                            "Descripción del curso 3.",
                            true);
                    newCourse = courseService.create(companyDTO);
                    System.out.println(". Course inserted into the database: " + newCourse);
                }
            }

            System.out.println("*** Initial data loaded successfully ***");
        };
    }
}