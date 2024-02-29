package com.example.mission3.domain.admin.service;

import com.example.mission3.domain.admin.dto.AdminRequestDto.SignupAdminRequestDto;
import com.example.mission3.domain.admin.dto.AdminResponseDto.SignupAdminResponseDto;
import com.example.mission3.domain.admin.entity.Admin;
import com.example.mission3.domain.admin.repository.AdminRepository;
import com.example.mission3.global.handler.exception.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.mission3.domain.admin.entity.type.AuthorityType.MANAGER;
import static com.example.mission3.domain.admin.entity.type.DepartmentType.MARKETING;
import static com.example.mission3.global.handler.exception.ErrorCode.CURRICULUM_MANAGER_PERMISSION_REQUIRED;
import static com.example.mission3.global.handler.exception.ErrorCode.EMAIL_ALREADY_EXISTS;

@RequiredArgsConstructor
@Service
public class AdminAuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignupAdminResponseDto signup(SignupAdminRequestDto requestDto) {
        if (adminRepository.existsByEmail(requestDto.getEmail())) {
            throw new CustomApiException(EMAIL_ALREADY_EXISTS.getMessage());
        }
        if (requestDto.getDepartment() == MARKETING && requestDto.getAuthority() == MANAGER) {
            throw new CustomApiException(CURRICULUM_MANAGER_PERMISSION_REQUIRED.getMessage());
        }

        String encoded = passwordEncoder.encode(requestDto.getPassword());
        Admin admin = adminRepository.save(requestDto.toEntity(encoded));
        return new SignupAdminResponseDto(admin);
    }
}
