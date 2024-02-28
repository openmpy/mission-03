package com.example.mission3.domain.lecture.repository;

import com.example.mission3.domain.lecture.entity.Lecture;
import com.example.mission3.domain.lecture.entity.type.CategoryType;
import com.example.mission3.domain.teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    List<Lecture> findAllByTeacherOrderByCreatedAtDesc(Teacher teacher);

    List<Lecture> findAllByCategoryOrderByCreatedAtDesc(CategoryType category);
}
