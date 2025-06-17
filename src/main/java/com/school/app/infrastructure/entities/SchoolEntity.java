package com.school.app.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "school")
public class SchoolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String address;

    @Column(unique = true)
    private String username;

    private String password;

    private Boolean enabled;

    @OneToMany(mappedBy = "school")
    private List<UserEntity> users;

    @OneToMany(mappedBy = "school")
    private List<CourseEntity> courses;
}
