package com.example.mission3.domain.admin.dto;

import com.example.mission3.domain.admin.entity.Admin;
import com.example.mission3.domain.admin.entity.type.AuthorityType;
import com.example.mission3.domain.admin.entity.type.DepartmentType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

public class AdminRequestDto {

    @Getter
    public static class SignupAdminRequestDto {

        @Email(message = "올바른 이메일 형식이 아닙니다.")
        private String email;

        @Pattern(
                message = "최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자로 구성되어야 합니다.",
                regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,15}$"
        )
        private String password;

        private DepartmentType department;
        private AuthorityType authority;

        public Admin toEntity(String encryptedPassword) {
            return Admin.builder()
                    .email(this.email)
                    .password(encryptedPassword)
                    .department(this.department)
                    .authority(this.authority)
                    .build();
        }
    }

    @Getter
    public static class SigninAdminRequestDto {

        @NotBlank(message = "이메일을 입력해주세요.")
        private String username;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;
    }
}
