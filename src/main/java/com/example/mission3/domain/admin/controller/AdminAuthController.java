package com.example.mission3.domain.admin.controller;

import com.example.mission3.domain.admin.dto.AdminRequestDto.SignupAdminRequestDto;
import com.example.mission3.domain.admin.dto.AdminResponseDto.SignupAdminResponseDto;
import com.example.mission3.domain.admin.service.AdminAuthService;
import com.example.mission3.global.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/admins")
@RestController
public class AdminAuthController {

    private final AdminAuthService adminAuthService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public ResponseDto<SignupAdminResponseDto> signup(@RequestBody @Valid SignupAdminRequestDto requestDto, BindingResult bindingResult) {
        SignupAdminResponseDto responseDto = adminAuthService.signup(requestDto);
        return new ResponseDto<>(true, "관리자 가입 기능", responseDto);
    }
}
