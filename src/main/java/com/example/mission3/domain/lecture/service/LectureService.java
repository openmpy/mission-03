package com.example.mission3.domain.lecture.service;

import com.example.mission3.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import com.example.mission3.domain.lecture.dto.LectureRequestDto.EditLectureRequestDto;
import com.example.mission3.domain.lecture.dto.LectureResponseDto.CreateLectureResponseDto;
import com.example.mission3.domain.lecture.dto.LectureResponseDto.EditLectureResponseDto;
import com.example.mission3.domain.lecture.dto.LectureResponseDto.GetLectureFromTeacherResponseDto;
import com.example.mission3.domain.lecture.dto.LectureResponseDto.GetLectureResponseDto;
import com.example.mission3.domain.lecture.entity.Lecture;
import com.example.mission3.domain.lecture.repository.LectureRepository;
import com.example.mission3.domain.teacher.entity.Teacher;
import com.example.mission3.domain.teacher.repository.TeacherRepository;
import com.example.mission3.global.handler.exception.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public EditLectureResponseDto edit(Long id, EditLectureRequestDto requestDto) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(() ->
                new CustomApiException("찾을 수 없는 강의 번호입니다.")
        );

        lecture.update(requestDto.getTitle(), requestDto.getPrice(), requestDto.getIntroduction(), requestDto.getCategory());
        return new EditLectureResponseDto(lecture);
    }

    @Transactional(readOnly = true)
    public GetLectureResponseDto get(Long id) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(() ->
                new CustomApiException("찾을 수 없는 강의 번호입니다.")
        );

        return new GetLectureResponseDto(lecture);
    }

    @Transactional(readOnly = true)
    public GetLectureFromTeacherResponseDto getFromTeacher(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() ->
                new CustomApiException("찾을 수 없는 강사 번호입니다.")
        );

        List<GetLectureResponseDto> lectureResponseDtoList = lectureRepository.findAllByTeacherOrderByCreatedAtDesc(teacher).stream()
                .map(GetLectureResponseDto::new)
                .toList();

        return new GetLectureFromTeacherResponseDto(teacherId, lectureResponseDtoList);
    }
}
