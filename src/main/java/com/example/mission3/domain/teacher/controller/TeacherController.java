package com.example.mission3.domain.teacher.controller;

import com.example.mission3.domain.teacher.dto.TeacherRequestDto.CreateTeacherRequestDto;
import com.example.mission3.domain.teacher.dto.TeacherRequestDto.EditTeacherRequestDto;
import com.example.mission3.domain.teacher.dto.TeacherResponseDto.CreateTeacherResponseDto;
import com.example.mission3.domain.teacher.dto.TeacherResponseDto.EditTeacherResponseDto;
import com.example.mission3.domain.teacher.dto.TeacherResponseDto.GetTeacherResponseDto;
import com.example.mission3.domain.teacher.service.TeacherService;
import com.example.mission3.global.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(
            @PathVariable Long id,
            @RequestBody @Valid EditTeacherRequestDto requestDto,
            BindingResult bindingResult
    ) {
        EditTeacherResponseDto responseDto = teacherService.edit(id, requestDto);
        return ResponseEntity.ok().body(
                new ResponseDto<>(true, "선택한 강사 정보 수정 기능", responseDto)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        GetTeacherResponseDto responseDto = teacherService.get(id);
        return ResponseEntity.ok().body(
                new ResponseDto<>(true, "선택한 강사 조회 기능", responseDto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        teacherService.delete(id);
        return ResponseEntity.ok().body(
                new ResponseDto<>(true, "선택한 강사 삭제 기능", null)
        );
    }
}
