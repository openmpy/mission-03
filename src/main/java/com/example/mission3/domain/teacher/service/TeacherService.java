package com.example.mission3.domain.teacher.service;

import com.example.mission3.domain.admin.repository.AdminRepository;
import com.example.mission3.domain.teacher.dto.TeacherRequestDto.CreateTeacherRequestDto;
import com.example.mission3.domain.teacher.dto.TeacherRequestDto.EditTeacherRequestDto;
import com.example.mission3.domain.teacher.dto.TeacherResponseDto.CreateTeacherResponseDto;
import com.example.mission3.domain.teacher.dto.TeacherResponseDto.EditTeacherResponseDto;
import com.example.mission3.domain.teacher.dto.TeacherResponseDto.GetTeacherResponseDto;
import com.example.mission3.domain.teacher.entity.Teacher;
import com.example.mission3.domain.teacher.repository.TeacherRepository;
import com.example.mission3.global.handler.exception.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.mission3.global.handler.exception.ErrorCode.*;

@RequiredArgsConstructor
@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final AdminRepository adminRepository;

    @Transactional
    public CreateTeacherResponseDto create(String email, CreateTeacherRequestDto requestDto) {
        if (!adminRepository.existsByEmail(email)) {
            throw new CustomApiException(ADMIN_ACCOUNT_NOT_FOUND.getMessage());
        }

        Teacher teacher = teacherRepository.save(requestDto.toEntity());
        return new CreateTeacherResponseDto(teacher);
    }

    @Transactional
    public EditTeacherResponseDto edit(Long id, String email, EditTeacherRequestDto requestDto) {
        if (!adminRepository.existsByEmail(email)) {
            throw new CustomApiException(ADMIN_ACCOUNT_NOT_FOUND.getMessage());
        }
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() ->
                new CustomApiException(TEACHER_ID_NOT_FOUND.getMessage())
        );

        teacher.update(requestDto.getCareer(), requestDto.getCompany(), requestDto.getPhone(), requestDto.getIntroduction());
        return new EditTeacherResponseDto(teacher);
    }

    @Transactional(readOnly = true)
    public GetTeacherResponseDto get(Long id, String email) {
        if (!adminRepository.existsByEmail(email)) {
            throw new CustomApiException(ADMIN_ACCOUNT_NOT_FOUND.getMessage());
        }
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() ->
                new CustomApiException(TEACHER_ID_NOT_FOUND.getMessage())
        );

        return new GetTeacherResponseDto(teacher);
    }

    @Transactional
    public void delete(Long id, String email) {
        if (!adminRepository.existsByEmail(email)) {
            throw new CustomApiException(ADMIN_ACCOUNT_NOT_FOUND.getMessage());
        }
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() ->
                new CustomApiException(TEACHER_ID_NOT_FOUND.getMessage())
        );
        if (teacher.getIsDeleted()) {
            throw new CustomApiException(LECTURE_ALREADY_DELETED.getMessage());
        }

        teacher.delete(true);
    }
}
