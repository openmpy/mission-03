package com.example.mission3.domain.admin.controller;

import com.example.mission3.domain.admin.dto.AdminRequestDto.SignupAdminRequestDto;
import com.example.mission3.domain.admin.dto.AdminResponseDto.SignupAdminResponseDto;
import com.example.mission3.domain.admin.service.AdminAuthService;
import com.example.mission3.global.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Admin Auth API", description = "관리자 계정 관련된 API 정보를 담고 있습니다.")
@RequiredArgsConstructor
@RequestMapping("/api/v1/admins")
@RestController
public class AdminAuthController {

    private final AdminAuthService adminAuthService;

    @Operation(summary = "관리자 가입 기능", description = "관리자 계정을 가입할 수 있는 API")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public ResponseDto<SignupAdminResponseDto> signup(@RequestBody @Valid SignupAdminRequestDto requestDto, BindingResult bindingResult) {
        SignupAdminResponseDto responseDto = adminAuthService.signup(requestDto);
        return ResponseDto.success("관리자 가입 기능", responseDto);
    }
}
