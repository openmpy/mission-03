package com.example.mission3.domain.teacher.service;

import com.example.mission3.domain.teacher.dto.TeacherRequestDto.CreateTeacherRequestDto;
import com.example.mission3.domain.teacher.dto.TeacherResponseDto.CreateTeacherResponseDto;
import com.example.mission3.domain.teacher.entity.Teacher;
import com.example.mission3.domain.teacher.repository.TeacherRepository;
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
}
