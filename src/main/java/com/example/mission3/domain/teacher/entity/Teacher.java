package com.example.mission3.domain.teacher.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@DynamicUpdate
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer career;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String phone;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String introduction;

    @Column(nullable = false)
    private Boolean isDeleted;

    public void update(Integer career, String company, String phone, String introduction) {
        if (career != null) {
            this.career = career;
        }
        if (company != null) {
            this.company = company;
        }
        if (phone != null) {
            this.phone = phone;
        }
        if (introduction != null) {
            this.introduction = introduction;
        }
    }

    public void delete(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }
}
