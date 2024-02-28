package com.example.mission3.domain.admin.service;

import com.example.mission3.domain.admin.dto.AdminRequestDto.CreateAdminRequestDto;
import com.example.mission3.domain.admin.dto.AdminResponseDto.CreateAdminResponseDto;
import com.example.mission3.domain.admin.entity.Admin;
import com.example.mission3.domain.admin.repository.AdminRepository;
import com.example.mission3.global.handler.exception.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.mission3.domain.admin.entity.type.AuthorityType.MANAGER;
import static com.example.mission3.domain.admin.entity.type.DepartmentType.MARKETING;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Transactional
    public CreateAdminResponseDto create(CreateAdminRequestDto requestDto) {
        if (adminRepository.existsByEmail(requestDto.getEmail())) {
            throw new CustomApiException("이미 가입된 이메일입니다.");
        }
        if (requestDto.getDepartment() == MARKETING && requestDto.getAuthority() == MANAGER) {
            throw new CustomApiException("커리큘럼, 개발 부서만 MANAGER 권한을 부여 받을 수 있습니다.");
        }

        Admin admin = adminRepository.save(requestDto.toEntity());
        return new CreateAdminResponseDto(admin);
    }
}
