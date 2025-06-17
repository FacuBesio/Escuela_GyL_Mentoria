package com.school.app.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;

    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private SchoolEntity school;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Course_UserEntity> course_users;

}
