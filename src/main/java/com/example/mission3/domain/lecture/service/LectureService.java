package com.example.mission3.domain.lecture.service;

import com.example.mission3.domain.admin.repository.AdminRepository;
import com.example.mission3.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import com.example.mission3.domain.lecture.dto.LectureRequestDto.EditLectureRequestDto;
import com.example.mission3.domain.lecture.dto.LectureResponseDto.CreateLectureResponseDto;
import com.example.mission3.domain.lecture.dto.LectureResponseDto.EditLectureResponseDto;
import com.example.mission3.domain.lecture.dto.LectureResponseDto.GetLectureFromTeacherResponseDto;
import com.example.mission3.domain.lecture.dto.LectureResponseDto.GetLectureResponseDto;
import com.example.mission3.domain.lecture.entity.Lecture;
import com.example.mission3.domain.lecture.entity.type.CategoryType;
import com.example.mission3.domain.lecture.repository.LectureRepository;
import com.example.mission3.domain.teacher.entity.Teacher;
import com.example.mission3.domain.teacher.repository.TeacherRepository;
import com.example.mission3.global.handler.exception.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.mission3.global.handler.exception.ErrorCode.*;

@RequiredArgsConstructor
@Service
public class LectureService {

    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;
    private final AdminRepository adminRepository;

    @Transactional
    public CreateLectureResponseDto create(String email, CreateLectureRequestDto requestDto) {
        if (!adminRepository.existsByEmail(email)) {
            throw new CustomApiException(ADMIN_ACCOUNT_NOT_FOUND.getMessage());
        }
        Teacher teacher = teacherRepository.findById(requestDto.getTeacherId()).orElseThrow(() ->
                new CustomApiException(TEACHER_ID_NOT_FOUND.getMessage())
        );

        Lecture lecture = lectureRepository.save(requestDto.toEntity(teacher));
        return new CreateLectureResponseDto(lecture);
    }

    @Transactional
    public EditLectureResponseDto edit(Long id, String email, EditLectureRequestDto requestDto) {
        if (!adminRepository.existsByEmail(email)) {
            throw new CustomApiException(ADMIN_ACCOUNT_NOT_FOUND.getMessage());
        }
        Lecture lecture = lectureRepository.findById(id).orElseThrow(() ->
                new CustomApiException(LECTURE_ID_NOT_FOUND.getMessage())
        );

        lecture.update(requestDto.getTitle(), requestDto.getPrice(), requestDto.getIntroduction(), requestDto.getCategory());
        return new EditLectureResponseDto(lecture);
    }

    @Transactional(readOnly = true)
    public GetLectureResponseDto get(Long id, String email) {
        if (!adminRepository.existsByEmail(email)) {
            throw new CustomApiException(ADMIN_ACCOUNT_NOT_FOUND.getMessage());
        }
        Lecture lecture = lectureRepository.findById(id).orElseThrow(() ->
                new CustomApiException(LECTURE_ID_NOT_FOUND.getMessage())
        );

        return new GetLectureResponseDto(lecture);
    }

    @Transactional(readOnly = true)
    public GetLectureFromTeacherResponseDto getFromTeacher(Long teacherId, String email) {
        if (!adminRepository.existsByEmail(email)) {
            throw new CustomApiException(ADMIN_ACCOUNT_NOT_FOUND.getMessage());
        }
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() ->
                new CustomApiException(TEACHER_ID_NOT_FOUND.getMessage())
        );

        List<GetLectureResponseDto> lectureResponseDtoList = lectureRepository.findAllByTeacherOrderByCreatedAtDesc(teacher).stream()
                .map(GetLectureResponseDto::new)
                .toList();

        return new GetLectureFromTeacherResponseDto(teacherId, lectureResponseDtoList);
    }

    @Transactional(readOnly = true)
    public List<GetLectureResponseDto> getFromCategory(String email, CategoryType category) {
        if (!adminRepository.existsByEmail(email)) {
            throw new CustomApiException(ADMIN_ACCOUNT_NOT_FOUND.getMessage());
        }

        return lectureRepository.findAllByCategoryOrderByCreatedAtDesc(category).stream()
                .map(GetLectureResponseDto::new)
                .toList();
    }

    @Transactional
    public void delete(Long id, String email) {
        if (!adminRepository.existsByEmail(email)) {
            throw new CustomApiException(ADMIN_ACCOUNT_NOT_FOUND.getMessage());
        }
        Lecture lecture = lectureRepository.findById(id).orElseThrow(() ->
                new CustomApiException(LECTURE_ID_NOT_FOUND.getMessage())
        );
        if (lecture.isDeleted()) {
            throw new CustomApiException(LECTURE_ALREADY_DELETED.getMessage());
        }

        lecture.delete(true);
    }
}
