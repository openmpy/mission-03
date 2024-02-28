package com.example.mission3.domain.teacher.service;

import com.example.mission3.domain.teacher.dto.TeacherRequestDto.CreateTeacherRequestDto;
import com.example.mission3.domain.teacher.dto.TeacherRequestDto.EditTeacherRequestDto;
import com.example.mission3.domain.teacher.dto.TeacherResponseDto.CreateTeacherResponseDto;
import com.example.mission3.domain.teacher.dto.TeacherResponseDto.EditTeacherResponseDto;
import com.example.mission3.domain.teacher.entity.Teacher;
import com.example.mission3.domain.teacher.repository.TeacherRepository;
import com.example.mission3.global.handler.exception.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Transactional
    public CreateTeacherResponseDto create(CreateTeacherRequestDto requestDto) {
        Teacher teacher = teacherRepository.save(requestDto.toEntity());
        return new CreateTeacherResponseDto(teacher);
    }

    @Transactional
    public EditTeacherResponseDto edit(Long id, EditTeacherRequestDto requestDto) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() ->
                new CustomApiException("찾을 수 없는 강사 번호입니다.")
        );

        teacher.update(requestDto.getCareer(), requestDto.getCompany(), requestDto.getPhone(), requestDto.getIntroduction());
        return new EditTeacherResponseDto(teacher);
    }
}
