package com.example.mission3.domain.lecture.controller;

import com.example.mission3.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import com.example.mission3.domain.lecture.dto.LectureRequestDto.EditLectureRequestDto;
import com.example.mission3.domain.lecture.dto.LectureResponseDto.CreateLectureResponseDto;
import com.example.mission3.domain.lecture.dto.LectureResponseDto.EditLectureResponseDto;
import com.example.mission3.domain.lecture.service.LectureService;
import com.example.mission3.global.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
}
