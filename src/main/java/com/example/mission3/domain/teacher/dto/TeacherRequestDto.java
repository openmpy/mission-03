package com.example.mission3.domain.teacher.dto;

import com.example.mission3.domain.teacher.entity.Teacher;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

public class TeacherRequestDto {

    @Getter
    public static class CreateTeacherRequestDto {

        @NotBlank(message = "이름을 입력해주세요.")
        private String name;

        @NotNull(message = "경력(년차)를 입력해주세요.")
        private Integer career;

        @NotBlank(message = "회사를 입력해주세요.")
        private String company;

        @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "올바르지 않은 전화번호입니다.")
        private String phone;

        @NotBlank(message = "소개를 입력해주세요.")
        private String introduction;

        public Teacher toEntity() {
            return Teacher.builder()
                    .name(this.name)
                    .career(this.career)
                    .company(this.company)
                    .phone(this.phone)
                    .introduction(this.introduction)
                    .build();
        }
    }
}
