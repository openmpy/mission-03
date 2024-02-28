package com.example.mission3.domain.teacher.repository;

import com.example.mission3.domain.teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
