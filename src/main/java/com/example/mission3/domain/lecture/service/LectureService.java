package com.example.mission3.domain.lecture.service;

import com.example.mission3.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import com.example.mission3.domain.lecture.dto.LectureResponseDto.CreateLectureResponseDto;
import com.example.mission3.domain.lecture.entity.Lecture;
import com.example.mission3.domain.lecture.repository.LectureRepository;
import com.example.mission3.domain.teacher.entity.Teacher;
import com.example.mission3.domain.teacher.repository.TeacherRepository;
import com.example.mission3.global.handler.exception.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LectureService {

    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;

    @Transactional
    public CreateLectureResponseDto create(CreateLectureRequestDto requestDto) {
        Teacher teacher = teacherRepository.findById(requestDto.getTeacherId()).orElseThrow(() ->
                new CustomApiException("찾을 수 없는 강사 번호입니다.")
        );

        Lecture lecture = lectureRepository.save(requestDto.toEntity(teacher));
        return new CreateLectureResponseDto(lecture);
    }
}
