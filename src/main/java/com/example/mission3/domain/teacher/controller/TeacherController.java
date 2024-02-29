package com.example.mission3.domain.teacher.controller;

import com.example.mission3.domain.teacher.dto.TeacherRequestDto.CreateTeacherRequestDto;
import com.example.mission3.domain.teacher.dto.TeacherRequestDto.EditTeacherRequestDto;
import com.example.mission3.domain.teacher.dto.TeacherResponseDto.CreateTeacherResponseDto;
import com.example.mission3.domain.teacher.dto.TeacherResponseDto.EditTeacherResponseDto;
import com.example.mission3.domain.teacher.dto.TeacherResponseDto.GetTeacherResponseDto;
import com.example.mission3.domain.teacher.service.TeacherService;
import com.example.mission3.global.dto.ResponseDto;
import com.example.mission3.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.example.mission3.domain.admin.entity.type.AuthorityType.Authority;

@RequiredArgsConstructor
@RequestMapping("/api/v1/teachers")
@RestController
public class TeacherController {

    private final TeacherService teacherService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseDto<CreateTeacherResponseDto> create(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid CreateTeacherRequestDto requestDto,
            BindingResult bindingResult
    ) {
        CreateTeacherResponseDto responseDto = teacherService.create(userDetails.getUsername(), requestDto);
        return ResponseDto.success("강사 등록 기능", responseDto);
    }

    @Secured(Authority.MANAGER)
    @PutMapping("/{id}")
    public ResponseDto<EditTeacherResponseDto> edit(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid EditTeacherRequestDto requestDto,
            BindingResult bindingResult
    ) {
        EditTeacherResponseDto responseDto = teacherService.edit(id, userDetails.getUsername(), requestDto);
        return ResponseDto.success("선택한 강사 정보 수정 기능", responseDto);
    }

    @GetMapping("/{id}")
    public ResponseDto<GetTeacherResponseDto> get(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        GetTeacherResponseDto responseDto = teacherService.get(id, userDetails.getUsername());
        return ResponseDto.success("선택한 강사 조회 기능", responseDto);
    }

    @Secured(Authority.MANAGER)
    @DeleteMapping("/{id}")
    public ResponseDto<Void> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        teacherService.delete(id, userDetails.getUsername());
        return ResponseDto.success("선택한 강사 삭제 기능", null);
    }
}
