package com.example.mission3.domain.teacher.controller;

import com.example.mission3.domain.teacher.dto.TeacherRequestDto.CreateTeacherRequestDto;
import com.example.mission3.domain.teacher.dto.TeacherResponseDto.CreateTeacherResponseDto;
import com.example.mission3.domain.teacher.service.TeacherService;
import com.example.mission3.global.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/teachers")
@RestController
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateTeacherRequestDto requestDto, BindingResult bindingResult) {
        CreateTeacherResponseDto responseDto = teacherService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto<>(true, "강사 등록 기능", responseDto)
        );
    }
}
