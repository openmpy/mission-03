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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.mission3.domain.admin.entity.type.AuthorityType.Authority;

@Tag(name = "Lecture API", description = "강의 관련된 API 정보를 담고 있습니다.")
@RequiredArgsConstructor
@RequestMapping("/api/v1/lectures")
@RestController
public class LectureController {

    private final LectureService lectureService;

    @Operation(summary = "강의 등록 기능", description = "강의를 등록할 수 있는 API")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseDto<CreateLectureResponseDto> create(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid CreateLectureRequestDto requestDto,
            BindingResult bindingResult
    ) {
        CreateLectureResponseDto responseDto = lectureService.create(userDetails.getUsername(), requestDto);
        return ResponseDto.success("강의 등록 기능", responseDto);
    }

    @Operation(summary = "선택한 강의 정보 수정 기능", description = "선택한 강의 정보를 수정할 수 있는 API")
    @Secured(Authority.MANAGER)
    @PutMapping("/{id}")
    public ResponseDto<EditLectureResponseDto> edit(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid EditLectureRequestDto requestDto,
            BindingResult bindingResult
    ) {
        EditLectureResponseDto responseDto = lectureService.edit(id, userDetails.getUsername(), requestDto);
        return ResponseDto.success("선택한 강의 정보 수정 기능", responseDto);
    }

    @Operation(summary = "선택한 강의 조회 기능", description = "선택한 강의 정보를 조회할 수 있는 API")
    @GetMapping("/{id}")
    public ResponseDto<GetLectureResponseDto> get(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        GetLectureResponseDto responseDto = lectureService.get(id, userDetails.getUsername());
        return ResponseDto.success("선택한 강의 조회 기능", responseDto);
    }

    @Operation(summary = "선택한 강사가 촬영한 강의 목록 조회 기능", description = "선택한 강사가 촬영한 강의 목록을 조회할 수 있는 API")
    @GetMapping("/teachers/{teacherId}")
    public ResponseDto<GetLectureFromTeacherResponseDto> getFromTeacher(
            @PathVariable Long teacherId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        GetLectureFromTeacherResponseDto responseDto = lectureService.getFromTeacher(teacherId, userDetails.getUsername());
        return ResponseDto.success("선택한 강사가 촬영한 강의 목록 조회 기능", responseDto);
    }

    @Operation(summary = "카테고리별 강의 목록 조회 기능", description = "카테고리별 강의 목록을 조회할 수 있는 API")
    @GetMapping
    public ResponseDto<List<GetLectureResponseDto>> getFromCategory(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam CategoryType category
    ) {
        List<GetLectureResponseDto> responseDtoList = lectureService.getFromCategory(userDetails.getUsername(), category);
        return ResponseDto.success("카테고리별 강의 목록 조회 기능", responseDtoList);
    }

    @Operation(summary = "선택한 강의 삭제 기능", description = "선택한 강의를 삭제할 수 있는 API")
    @Secured(Authority.MANAGER)
    @DeleteMapping("/{id}")
    public ResponseDto<Void> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        lectureService.delete(id, userDetails.getUsername());
        return ResponseDto.success("선택한 강의 삭제 기능", null);
    }
}
