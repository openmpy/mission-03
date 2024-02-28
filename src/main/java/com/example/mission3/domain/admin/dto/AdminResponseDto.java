package com.example.mission3.domain.admin.dto;

import com.example.mission3.domain.admin.entity.Admin;
import lombok.Getter;

public class AdminResponseDto {

    @Getter
    public static class CreateAdminResponseDto {

        private final String email;
        private final String department;
        private final String authority;

        public CreateAdminResponseDto(Admin admin) {
            this.email = admin.getEmail();
            this.department = admin.getDepartment().getValue();
            this.authority = admin.getAuthority().name();
        }
    }
}
