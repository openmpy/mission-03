package com.example.mission3.domain.admin.entity;

import com.example.mission3.domain.admin.entity.type.AuthorityType;
import com.example.mission3.domain.admin.entity.type.DepartmentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DepartmentType department;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthorityType authority;
}
