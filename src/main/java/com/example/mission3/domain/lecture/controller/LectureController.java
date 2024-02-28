package com.example.mission3.domain.lecture.controller;

import com.example.mission3.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import com.example.mission3.domain.lecture.dto.LectureResponseDto.CreateLectureResponseDto;
import com.example.mission3.domain.lecture.service.LectureService;
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
}
