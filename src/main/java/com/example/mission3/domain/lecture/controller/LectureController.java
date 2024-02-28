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
import com.example.mission3.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.mission3.domain.admin.entity.type.AuthorityType.Authority.MANAGER;

@RequiredArgsConstructor
@RequestMapping("/api/v1/lectures")
@RestController
public class LectureController {

    private final LectureService lectureService;

    @PostMapping
    public ResponseEntity<?> create(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid CreateLectureRequestDto requestDto,
            BindingResult bindingResult
    ) {
        CreateLectureResponseDto responseDto = lectureService.create(userDetails.getUsername(), requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto<>(true, "강의 등록 기능", responseDto)
        );
    }

    @Secured(MANAGER)
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid EditLectureRequestDto requestDto,
            BindingResult bindingResult
    ) {
        EditLectureResponseDto responseDto = lectureService.edit(id, userDetails.getUsername(), requestDto);
        return ResponseEntity.ok().body(
                new ResponseDto<>(true, "선택한 강의 정보 수정 기능", responseDto)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        GetLectureResponseDto responseDto = lectureService.get(id, userDetails.getUsername());
        return ResponseEntity.ok().body(
                new ResponseDto<>(true, "선택한 강의 조회 기능", responseDto)
        );
    }

    @GetMapping("/teachers/{teacherId}")
    public ResponseEntity<?> getFromTeacher(
            @PathVariable Long teacherId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        GetLectureFromTeacherResponseDto responseDto = lectureService.getFromTeacher(teacherId, userDetails.getUsername());
        return ResponseEntity.ok().body(
                new ResponseDto<>(true, "선택한 강사가 촬영한 강의 목록 조회 기능", responseDto)
        );
    }

    @GetMapping
    public ResponseEntity<?> getFromCategory(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam CategoryType category
    ) {
        List<GetLectureResponseDto> responseDtoList = lectureService.getFromCategory(userDetails.getUsername(), category);
        return ResponseEntity.ok().body(
                new ResponseDto<>(true, "카테고리별 강의 목록 조회 기능", responseDtoList)
        );
    }

    @Secured(MANAGER)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        lectureService.delete(id, userDetails.getUsername());
        return ResponseEntity.ok().body(
                new ResponseDto<>(true, "선택한 강의 삭제 기능", null)
        );
    }
}
