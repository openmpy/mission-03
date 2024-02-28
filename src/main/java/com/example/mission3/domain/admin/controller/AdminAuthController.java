package com.example.mission3.domain.admin.controller;

import com.example.mission3.domain.admin.dto.AdminRequestDto.SignupAdminRequestDto;
import com.example.mission3.domain.admin.dto.AdminResponseDto.SignupAdminResponseDto;
import com.example.mission3.domain.admin.service.AdminAuthService;
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
@RequestMapping("/api/v1/admins")
@RestController
public class AdminAuthController {

    private final AdminAuthService adminAuthService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignupAdminRequestDto requestDto, BindingResult bindingResult) {
        SignupAdminResponseDto responseDto = adminAuthService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto<>(true, "관리자 가입 기능", responseDto)
        );
    }
}
