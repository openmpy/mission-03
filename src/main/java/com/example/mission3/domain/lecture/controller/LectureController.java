package com.example.mission3.domain.lecture.controller;

import com.example.mission3.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import com.example.mission3.domain.lecture.dto.LectureRequestDto.EditLectureRequestDto;
import com.example.mission3.domain.lecture.dto.LectureResponseDto.CreateLectureResponseDto;
import com.example.mission3.domain.lecture.dto.LectureResponseDto.EditLectureResponseDto;
import com.example.mission3.domain.lecture.dto.LectureResponseDto.GetLectureFromTeacherResponseDto;
import com.example.mission3.domain.lecture.dto.LectureResponseDto.GetLectureResponseDto;
import com.example.mission3.domain.lecture.entity.type.CategoryType;
import com.example.mission3.domain.lecture.service.LectureService;
import com.example.mission3.global.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/lectures")
@RestController
public class LectureController {

    private final LectureService lectureService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateLectureRequestDto requestDto, BindingResult bindingResult) {
        CreateLectureResponseDto responseDto = lectureService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto<>(true, "강의 등록 기능", responseDto)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(
            @PathVariable Long id,
            @RequestBody @Valid EditLectureRequestDto requestDto,
            BindingResult bindingResult
    ) {
        EditLectureResponseDto responseDto = lectureService.edit(id, requestDto);
        return ResponseEntity.ok().body(
                new ResponseDto<>(true, "선택한 강의 정보 수정 기능", responseDto)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        GetLectureResponseDto responseDto = lectureService.get(id);
        return ResponseEntity.ok().body(
                new ResponseDto<>(true, "선택한 강의 조회 기능", responseDto)
        );
    }

    @GetMapping("/teachers/{teacherId}")
    public ResponseEntity<?> getFromTeacher(@PathVariable Long teacherId) {
        GetLectureFromTeacherResponseDto responseDto = lectureService.getFromTeacher(teacherId);
        return ResponseEntity.ok().body(
                new ResponseDto<>(true, "선택한 강사가 촬영한 강의 목록 조회 기능", responseDto)
        );
    }

    @GetMapping
    public ResponseEntity<?> getFromCategory(@RequestParam CategoryType category) {
        List<GetLectureResponseDto> responseDtoList = lectureService.getFromCategory(category);
        return ResponseEntity.ok().body(
                new ResponseDto<>(true, "카테고리별 강의 목록 조회 기능", responseDtoList)
        );
    }
}
