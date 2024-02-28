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

@RequiredArgsConstructor
@Service
public class AdminAuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignupAdminResponseDto signup(SignupAdminRequestDto requestDto) {
        if (adminRepository.existsByEmail(requestDto.getEmail())) {
            throw new CustomApiException("이미 가입된 이메일입니다.");
        }
        if (requestDto.getDepartment() == MARKETING && requestDto.getAuthority() == MANAGER) {
            throw new CustomApiException("커리큘럼, 개발 부서만 MANAGER 권한을 부여 받을 수 있습니다.");
        }

        String encoded = passwordEncoder.encode(requestDto.getPassword());
        Admin admin = adminRepository.save(requestDto.toEntity(encoded));
        return new SignupAdminResponseDto(admin);
    }
}
