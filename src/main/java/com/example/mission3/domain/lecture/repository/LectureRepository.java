package com.example.mission3.domain.lecture.repository;

import com.example.mission3.domain.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
